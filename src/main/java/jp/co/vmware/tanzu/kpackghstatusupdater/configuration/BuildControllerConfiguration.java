package jp.co.vmware.tanzu.kpackghstatusupdater.configuration;

import io.kubernetes.client.extended.controller.Controller;
import io.kubernetes.client.extended.controller.builder.ControllerBuilder;
import io.kubernetes.client.extended.controller.reconciler.Reconciler;
import io.kubernetes.client.informer.SharedIndexInformer;
import io.kubernetes.client.informer.SharedInformerFactory;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.util.generic.GenericKubernetesApi;
import jp.co.vmware.tanzu.kpackghstatusupdater.models.V1alpha2Build;
import jp.co.vmware.tanzu.kpackghstatusupdater.models.V1alpha2BuildList;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BuildControllerConfiguration {

    @Bean
    SharedIndexInformer<V1alpha2Build> sharedIndexInformer(SharedInformerFactory sharedInformerFactory, GenericKubernetesApi<V1alpha2Build, V1alpha2BuildList> buildApi) {
        return sharedInformerFactory.sharedIndexInformerFor(buildApi, V1alpha2Build.class, 0);
    }

    @Bean
    public GenericKubernetesApi<V1alpha2Build, V1alpha2BuildList> buildApi(ApiClient apiClient) {
        return new GenericKubernetesApi<>(V1alpha2Build.class, V1alpha2BuildList.class, "kpack.io",
                "v1alpha2", "builds", apiClient);
    }


    @Bean
    Controller controller(SharedInformerFactory sharedInformerFactory,
                          Reconciler reconciler,
                          SharedIndexInformer<V1alpha2Build> shareIndexInformer) {
        return ControllerBuilder
                .defaultBuilder(sharedInformerFactory)
                .watch(contrWatchQueue -> ControllerBuilder
                        .controllerWatchBuilder(V1alpha2Build.class, contrWatchQueue)
                        .withResyncPeriod(Duration.of(1, ChronoUnit.SECONDS))
                        .build())
                // .withWorkerCount(2)
                .withReconciler(reconciler)
                .withReadyFunc(shareIndexInformer::hasSynced)
                .withName("build controller")
                .build();
    }

    @Bean
    ExecutorService executorService() {
        return Executors.newCachedThreadPool();
    }

    @Bean
    ApplicationRunner runner(ExecutorService executorService,
                             SharedInformerFactory sharedInformerFactory,
                             Controller controller) {
        return args -> executorService.execute(() -> {
            sharedInformerFactory.startAllRegisteredInformers();
            controller.run();
        });
    }


}
