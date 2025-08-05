package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.ProjectCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.*;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto.LabelsItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto.QueryDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto.QueryScope;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto.ServerDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PQLetaConfigManager implements PQLetaConfigManagerInterface {
    private final static Logger logger = LoggerFactory.getLogger(PQLetaConfigManager.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final CatalogManagerInterface catalogManager;
    private final ConfigurationRepositoryInterface configurationRepository;

    public PQLetaConfigManager(CatalogManagerInterface catalogManager, ConfigurationRepositoryInterface configurationRepository) {
        this.catalogManager = catalogManager;
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void makePQLetaConfig() throws OperationIncompleteException {
        ProjectCatalog projectCatalog = catalogManager.getProjectCatalog();

        List<PQLetaConfig> pqLetaConfigList = configurationRepository.getPQLetaConfigs();

        List<QueryScope> queryScopes = new ArrayList<>();
        for (PQLetaConfig config : pqLetaConfigList) {
            List<Host> hostList = config.getHosts();
            List<TypedImportItems> typedImports = config.getImportByType();
            String sourceURL = config.getSourceURL();

            ServerDescription serverDescription = ServerDescription.newBuilder()
                    .setSslVerify("False")
                    .setUrl(sourceURL)
                    .build();

            List<QueryDescriptionItem> queryDescriptionItems = new ArrayList<>();
            for (Host host : hostList) {
                queryDescriptionItems.addAll(generateQueryDescriptionItemListForHost(host));
            }

            queryDescriptionItems.addAll(getImportByTypeQueryDescriptionItem(typedImports));

            QueryScope queryScope = QueryScope.newBuilder()
                    .setServerDescription(serverDescription)
                    .setQueryDescription(queryDescriptionItems)
                    .build();

            queryScopes.add(queryScope);
        }

        try (Writer writer = new FileWriter(String.valueOf(projectCatalog.getConfigurationFilesCatalog().resolve("PrometheusQueryConfigurations.json")))) {
            gson.toJson(queryScopes, writer);
            logger.debug("PrometheusQueryConfigurations.json saved");
        } catch (IOException ioe) {
            logger.error("PrometheusQueryConfigurations.json generation error: {}", ioe.getMessage(), ioe);
            throw new OperationIncompleteException("PrometheusQueryConfigurations.json generation error: " + ioe.getMessage(), ioe);
        }

        pastePQLetaQueryTemplateFile();
    }

    private List<QueryDescriptionItem> generateQueryDescriptionItemListForHost(Host host) {
        List<QueryDescriptionItem> queryDescriptionItems = new ArrayList<>();

        // FS
        queryDescriptionItems.addAll(getFilesystemQueryDescriptionItem(host));

        // CPU
        queryDescriptionItems.add(getCPUQueryDescriptionItem(host));

        // SWAP
        queryDescriptionItems.add(getSWAPQueryDescriptionItem(host));

        // RAM
        queryDescriptionItems.add(getMemoryQueryDescriptionItem(host));

        return queryDescriptionItems;
    }

    private QueryDescriptionItem getMemoryQueryDescriptionItem(Host host) {
        List<LabelsItem> labels = new ArrayList<>();
        labels.add(LabelsItem.newBuilder()
                .setKey("host_identity")
                .setSign("=")
                .setValue(host.getHostName())
                .build());
        QueryDescriptionItem queryDescriptionItem = QueryDescriptionItem.newBuilder()
                .setType("memory")
                .setIdentifier("Status " + host.getHostName() + " ram")
                .setUpperLimit("90")
                .setLowerLimit("80")
                .setStep("7")
                .setTimeRange("9")
                .setLabels(labels)
                .build();
        return queryDescriptionItem;
    }

    private QueryDescriptionItem getSWAPQueryDescriptionItem(Host host) {
        List<LabelsItem> labels = new ArrayList<>();
        labels.add(LabelsItem.newBuilder()
                .setKey("host_identity")
                .setSign("=")
                .setValue(host.getHostName())
                .build());
        QueryDescriptionItem queryDescriptionItem = QueryDescriptionItem.newBuilder()
                .setType("swap")
                .setIdentifier("Status " + host.getHostName() + " swap")
                .setUpperLimit("90")
                .setLowerLimit("80")
                .setStep("7")
                .setTimeRange("9")
                .setLabels(labels)
                .build();
        return queryDescriptionItem;
    }

    private QueryDescriptionItem getCPUQueryDescriptionItem(Host host) {
        List<LabelsItem> labels = new ArrayList<>();
        labels.add(LabelsItem.newBuilder()
                .setKey("mode")
                .setSign("=")
                .setValue("idle")
                .build());
        labels.add(LabelsItem.newBuilder()
                .setKey("host_identity")
                .setSign("=")
                .setValue(host.getHostName())
                .build());
        QueryDescriptionItem queryDescriptionItem = QueryDescriptionItem.newBuilder()
                .setType("cpu")
                .setIdentifier("Status " + host.getHostName() + " cpu")
                .setUpperLimit("90")
                .setLowerLimit("80")
                .setStep("7")
                .setTimeRange("9")
                .setLabels(labels)
                .build();
        return queryDescriptionItem;
    }

    private List<QueryDescriptionItem> getFilesystemQueryDescriptionItem(Host host) {
        List<QueryDescriptionItem> queryDescriptionItems = new ArrayList<>();
        List<FsUnit> fsUnits = host.getFsUnits();
        for (FsUnit fsUnit : fsUnits) {
            List<LabelsItem> labels = new ArrayList<>();
            labels.add(LabelsItem.newBuilder()
                    .setKey("device")
                    .setSign("=")
                    .setValue(fsUnit.getMountpoint())
                    .build());
            labels.add(LabelsItem.newBuilder()
                    .setKey("host_identity")
                    .setSign("=")
                    .setValue(host.getHostName())
                    .build());
            QueryDescriptionItem queryDescriptionItem = QueryDescriptionItem.newBuilder()
                    .setType("filesystem")
                    .setIdentifier("Status " + host.getHostName() + " filesystem " + Paths.get(fsUnit.getMountpoint()).getFileName().toString())
                    .setUpperLimit("90")
                    .setLowerLimit("80")
                    .setStep("7")
                    .setTimeRange("9")
                    .setLabels(labels)
                    .build();
            queryDescriptionItems.add(queryDescriptionItem);
        }
        return queryDescriptionItems;
    }

    private List<QueryDescriptionItem> getImportByTypeQueryDescriptionItem(List<TypedImportItems> typedImports) {
        List<QueryDescriptionItem> queryDescriptionItems = new ArrayList<>();
        for (TypedImportItems typedImportItem : typedImports) {
            List<LabelsItem> labels = new ArrayList<>();
            for (FilterLabel filterLabel : typedImportItem.getLabelsForFiltering()) {
                labels.add(LabelsItem.newBuilder()
                        .setKey(filterLabel.getKey())
                        .setSign("=")
                        .setValue(filterLabel.getValue())
                        .build());
            }
            QueryDescriptionItem queryDescriptionItem = QueryDescriptionItem.newBuilder()
                    .setType(typedImportItem.getTypeName())
                    .setIdentifier(typedImportItem.getPqletaId())
                    .setUpperLimit(null)
                    .setLowerLimit(null)
                    .setStep("7")
                    .setTimeRange("9")
                    .setLabels(labels)
                    .build();
            queryDescriptionItems.add(queryDescriptionItem);
        }
        return queryDescriptionItems;
    }

    private void pastePQLetaQueryTemplateFile() throws OperationIncompleteException {
        try (InputStream pqletaConfig = PQLetaConfigManager.class.getClassLoader().getResourceAsStream("pqletaconfig/PrometheusQueryTemplates.txt")) {
            Path targetPath = catalogManager.getProjectCatalog().getConfigurationFilesCatalog().resolve("PrometheusQueryTemplates.txt");
            if (pqletaConfig == null)
                throw new OperationIncompleteException("Internal error: PQLeta config PrometheusQueryTemplates.txt was not found");
            try (OutputStream os = new FileOutputStream(String.valueOf(targetPath))) {
                byte[] buffer = new byte[8192];
                int length;
                while ((length = pqletaConfig.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
