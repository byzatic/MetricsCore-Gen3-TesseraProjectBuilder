package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto.*;

import java.util.ArrayList;
import java.util.List;

class ProjectGlobalGenerator {
    public ProjectGlobal generate() {
        List<StorageItem> storages = new ArrayList<>();
        List<ServiceItem> services = new ArrayList<>();

        storages.add(genPrometheusDataStorage());

        services.add(genPrometheusExportService());

        ProjectGlobal projectGlobal = ProjectGlobal.newBuilder()
                .setStorages(storages)
                .setServices(services)
                .build();
        return projectGlobal;
    }

    private StorageItem genPrometheusDataStorage() {
        List<StorageOptionItem> storageOptionItems = new ArrayList<>();

        StorageOptionItem optionLogDumpOnError = StorageOptionItem.newBuilder()
                .setKey("LogDumpOnError")
                .setValue("true")
                .build();
        storageOptionItems.add(optionLogDumpOnError);

        StorageOptionItem optionExceptOnCreate = StorageOptionItem.newBuilder()
                .setKey("ExceptOnCreate")
                .setValue("true")
                .build();
        storageOptionItems.add(optionExceptOnCreate);

        StorageItem storageItem = StorageItem.newBuilder()
                .setIdName("PROMETHEUS_DATA_STORAGE")
                .setDescription("Storage for publishing to Prometheus")
                .setOptions(storageOptionItems)
                .build();

        return storageItem;
    }

    private ServiceItem genPrometheusExportService() {
        List<ServiceOptionItem> serviceOptionItems = new ArrayList<>();

        ServiceOptionItem optionStorage = ServiceOptionItem.newBuilder()
                .setName("storage")
                .setData("PROMETHEUS_DATA_STORAGE")
                .build();
        serviceOptionItems.add(optionStorage);

        ServiceOptionItem optionExpiredMinutesAgo = ServiceOptionItem.newBuilder()
                .setName("expiredMinutesAgo")
                .setData("6")
                .build();
        serviceOptionItems.add(optionExpiredMinutesAgo);

        ServiceOptionItem optionApiURL = ServiceOptionItem.newBuilder()
                .setName("apiURL")
                .setData("http://0.0.0.0:8080/metrics")
                .build();
        serviceOptionItems.add(optionApiURL);

        ServiceOptionItem optionCronMetricUpdateString = ServiceOptionItem.newBuilder()
                .setName("cronMetricUpdateString")
                .setData("*/1 * * * * ?")
                .build();
        serviceOptionItems.add(optionCronMetricUpdateString);

        ServiceItem serviceItem = ServiceItem.newBuilder()
                .setIdName("PrometheusExportService")
                .setDescription("Service Prometheus data uploading (Poll)")
                .setOptions(serviceOptionItems)
                .build();

        return serviceItem;
    }
}
