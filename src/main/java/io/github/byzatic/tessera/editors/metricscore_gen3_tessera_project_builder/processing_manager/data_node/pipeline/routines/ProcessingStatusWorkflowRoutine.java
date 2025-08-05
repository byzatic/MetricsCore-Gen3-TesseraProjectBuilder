package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerOptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeStorageInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProcessingStatusWorkflowRoutine {
    private final NodeRepositoryInterface nodeRepository;

    private final SupportDSL supportDSL = new SupportDSL();

    public ProcessingStatusWorkflowRoutine(NodeRepositoryInterface nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public WorkerDescriptionItem create(NodeCatalog nodeCatalog, Node node) {
        try {
            List<WorkerOptionItem> workerOptionItems = new ArrayList<>();
            String dslFileName = "ProcessingStatus-ProcessingStatusWorkflowRoutine.mcg3dsl";

            WorkerOptionItem workerOptionItemDSLFile = WorkerOptionItem.newBuilder()
                    .setKey("MCg3-WorkflowRoutine-DSL-File")
                    .setValue("${NODE_PATH}/" + dslFileName)
                    .build();
            workerOptionItems.add(workerOptionItemDSLFile);

            WorkerDescriptionItem workerDescriptionItem = WorkerDescriptionItem.newBuilder()
                    .setName("ProcessingStatusWorkflowRoutine")
                    .setDescription("processing status workflow routine")
                    .setOptions(workerOptionItems)
                    .build();

            String dsl = getDSL(node);
            Path dslFile = nodeCatalog.getConfigurationFilesCatalog().resolve(dslFileName);
            supportDSL.writeDsl(dsl, dslFile);

            return workerDescriptionItem;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getDSL(Node node) {
        String saveStorage = "SELF_DATA_STORAGE";
        String getLIFTINGStorage = "LIFTING_DATA_STORAGE";
        String getSELFStorage = "SELF_DATA_STORAGE";
        boolean isDownstreamEmpty = node.getDownstream().isEmpty();
        // TODO: .replace('.', '_')
        String localMetricName = node.getNodeId() + "-StatusRef";

        NodeStorageInterface inputDataStorage = null;
        if (isDownstreamEmpty) {
            inputDataStorage = nodeRepository.getNodeStorage(node.getNodeId(), getSELFStorage);
        } else {
            inputDataStorage = nodeRepository.getNodeStorage(node.getNodeId(), getLIFTINGStorage);
        }
        NodeStorageInterface outputDataStorage = nodeRepository.getNodeStorage(node.getNodeId(), saveStorage);

        StringBuilder dslBuilder = new StringBuilder();

        dslBuilder.append("//\n");
        dslBuilder.append("// ProcessingStatusWorkflowRoutine\n");
        dslBuilder.append("//\n");
        dslBuilder.append("\n");

        dslBuilder.append("// get data\n");
        for (String dataId : inputDataStorage.list()) {
            dslBuilder.append("GET FROM SELF STORAGE ").append(inputDataStorage.getStorageName()).append(" BY DATA ID ").append(dataId).append(";\n");
        }

        dslBuilder.append("// process function ProcessStatus for diskSDA1Status\n");
        dslBuilder.append("PROCESS FUNCTION ProcessStatus(\n");
        for (String dataId : inputDataStorage.list()) {
            dslBuilder.append("\"DataId=").append(dataId).append("\",\n");
        }
        dslBuilder.append("\"MetricName=").append(node.getDataType()).append("\",\n");
        dslBuilder.append("\"PromLabel_").append(node.getDataType()).append("=").append(node.getDataName()).append("\",\n");
        if (node.getGrafanaURL() != null && !Objects.equals(node.getGrafanaURL(), "")) {
            dslBuilder.append("\"PromLabel_").append("url").append("=").append(node.getGrafanaURL()).append("\",\n");
        }
        dslBuilder.append(") RETURN ").append(localMetricName).append(";\n");
        dslBuilder.append("\n");

        dslBuilder.append("// Put data to local cross-WorkflowRoutine storage SELF_DATA_STORAGE\n");
        dslBuilder.append("PUT DATA ").append(localMetricName).append(" TO STORAGE ").append(outputDataStorage.getStorageName()).append(" MODIFIER local").append(" BY DATA ID ").append(localMetricName).append(";\n");
        dslBuilder.append("\n");

        outputDataStorage.put(localMetricName);
        nodeRepository.putNodeStorages(node.getNodeId(), outputDataStorage);

        String dsl = dslBuilder.toString();
        return dsl;
    }
}
