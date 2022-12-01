package jp.co.vmware.tanzu.kpackghstatusupdater.reconciler;

import io.kubernetes.client.extended.controller.reconciler.Reconciler;
import io.kubernetes.client.extended.controller.reconciler.Request;
import io.kubernetes.client.extended.controller.reconciler.Result;
import io.kubernetes.client.informer.SharedIndexInformer;
import jp.co.vmware.tanzu.kpackghstatusupdater.models.*;
import jp.co.vmware.tanzu.kpackghstatusupdater.repository.CommitStatusRepository;
import jp.co.vmware.tanzu.kpackghstatusupdater.utils.GithubStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BuildReconciler implements Reconciler {

    private final SharedIndexInformer<V1alpha2Build> buildSharedIndexInformer;

    private final CommitStatusRepository commitStatusRepository;

    private final GithubStatus githubStatus;

    public BuildReconciler(SharedIndexInformer<V1alpha2Build> buildSharedIndexInformer, CommitStatusRepository commitStatusRepository, GithubStatus githubStatus) {
        this.buildSharedIndexInformer = buildSharedIndexInformer;
        this.commitStatusRepository = commitStatusRepository;
        this.githubStatus = githubStatus;
    }

    @Override
    public Result reconcile(Request request) {

        String key = request.getNamespace() + "/" + request.getName();


        System.out.println(key);
        V1alpha2Build resourceInstance = buildSharedIndexInformer.getIndexer().getByKey(key);

        if (key.equals("buildspace/petclinic-build-29")) {
            V1alpha2BuildSpec spec = resourceInstance.getSpec();

        }

        Map<String, String> annotations = null;
        if (resourceInstance.getMetadata() != null) {
            annotations = resourceInstance.getMetadata().getAnnotations();
        }

        if (annotations != null) {
            if (Objects.equals(annotations.get("image.kpack.io/reason"), "COMMIT")) {
                V1alpha2BuildSpec spec = resourceInstance.getSpec();
                if (spec != null) {
                    V1alpha2BuildSpecSource source = spec.getSource();
                    if (source != null) {
                        V1alpha2BuildSpecSourceGit git = source.getGit();

                        if (git != null) {
                            System.out.println("aaa" + git);
                            String revision = git.getRevision();
                            String url = git.getUrl();
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
                                    CommitStatus commitStatus = new CommitStatus();
                                    String description = "Build for revision " + revision + " status :" + success;
                                    commitStatus.setId(revision);
                                    commitStatus.setId(description);
                                    commitStatusRepository.save(commitStatus);
                                    githubStatus.updateStatus(url, revision, success);
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
