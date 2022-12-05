package jp.co.vmware.tanzu.kpackghstatusupdater.reconciler;

import io.kubernetes.client.extended.controller.reconciler.Reconciler;
import io.kubernetes.client.extended.controller.reconciler.Request;
import io.kubernetes.client.extended.controller.reconciler.Result;
import io.kubernetes.client.informer.SharedIndexInformer;
import jp.co.vmware.tanzu.kpackghstatusupdater.models.*;
import jp.co.vmware.tanzu.kpackghstatusupdater.service.CommitStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BuildReconciler implements Reconciler {

    private static final Logger logger = LoggerFactory.getLogger(BuildReconciler.class);

    private final SharedIndexInformer<V1alpha2Build> buildSharedIndexInformer;

    private final CommitStatusService commitStatusService;

    public BuildReconciler(SharedIndexInformer<V1alpha2Build> buildSharedIndexInformer, CommitStatusService commitStatusService) {
        this.buildSharedIndexInformer = buildSharedIndexInformer;
        this.commitStatusService = commitStatusService;
    }

    @Override
    public Result reconcile(Request request) {

        String key = request.getNamespace() + "/" + request.getName();

        V1alpha2Build resourceInstance = buildSharedIndexInformer.getIndexer().getByKey(key);

        Map<String, String> annotations = null;
        if (resourceInstance != null) {
            if (resourceInstance.getMetadata() != null) {
                annotations = resourceInstance.getMetadata().getAnnotations();
            }
        } else {
            logger.info("detect kpack build deleted : " + key);
            commitStatusService.deleteStatus(key);
        }

        if (annotations != null) {
            if (Objects.equals(annotations.get("image.kpack.io/reason"), "COMMIT")) {
                logger.info("detect kpack build based on COMMIT :" + key);
                V1alpha2BuildSpec spec = resourceInstance.getSpec();
                if (spec != null) {
                    V1alpha2BuildSpecSource source = spec.getSource();
                    if (source != null) {
                        V1alpha2BuildSpecSourceGit git = source.getGit();

                        if (git != null) {
                            String revision = git.getRevision();
                            String url = git.getUrl();
                            logger.info("GitUrl :" + url + " Revision:" + revision);
                            if (revision != null && url != null) {

                                V1alpha2BuildStatus status = resourceInstance.getStatus();

                                if (status != null) {
                                    List<V1alpha2BuildStatusConditions> conditions = status.getConditions();
                                    String success = null;
                                    if (conditions != null) {
                                        if (conditions.size() > 0) {
                                            success = conditions.get(0).getStatus();
                                        } else {
                                            success = "Pending";
                                        }
                                    }
                                    List<String> tags = spec.getTags();
                                    logger.info("Attempting to update commit status");
                                    commitStatusService.updateStatus(key, url, revision, success, tags);
                                }
                            }
                        }
                    }
                }
            }
        }
        return new Result(false);
    }
}
