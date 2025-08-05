package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.external_mc_exporter.pipeline.routines;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.SupportDSL;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerOptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeStorageInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphLiftingDataWorkflowRoutine {

    private final NodeRepositoryInterface nodeRepository;
    private final ConfigurationRepositoryInterface configurationRepository;

    private final SupportDSL supportDSL = new SupportDSL();

    public GraphLiftingDataWorkflowRoutine(NodeRepositoryInterface nodeRepository, ConfigurationRepositoryInterface configurationRepository) {
        this.nodeRepository = nodeRepository;
        this.configurationRepository = configurationRepository;
    }

    public WorkerDescriptionItem create(NodeCatalog nodeCatalog, Node node) {
        try {
            List<WorkerOptionItem> workerOptionItems = new ArrayList<>();
            String dslFileName = "LiftingData-GraphLiftingDataWorkflowRoutine.mcg3dsl";

            WorkerOptionItem workerOptionItemDSLFile = WorkerOptionItem.newBuilder()
                    .setKey("MCg3-WorkflowRoutine-DSL-File")
                    .setValue("${NODE_PATH}/" + dslFileName)
                    .build();
            workerOptionItems.add(workerOptionItemDSLFile);

            WorkerDescriptionItem workerDescriptionItem = WorkerDescriptionItem.newBuilder()
                    .setName("GraphLiftingDataWorkflowRoutine")
                    .setDescription("graph lifting data workflow routine")
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

    private String getDSL(Node node) throws OperationIncompleteException {
        String saveStorage = "LIFTING_DATA_STORAGE";
        String liftingStorage = "PUBLIC_DATA_STORAGE";
        Map<String, List<String>> allData = new HashMap<>();
        for (String downstreamNodeName : node.getDownstream()) {
            NodeStorageInterface storage = nodeRepository.getNodeStorage(downstreamNodeName, liftingStorage);
            allData.put(
                    downstreamNodeName,
                    storage.list()
            );
        }

        NodeStorageInterface storageLiftingDataStorage = nodeRepository.getNodeStorage(node.getNodeId(), "LIFTING_DATA_STORAGE");

        StringBuilder dslBuilder = new StringBuilder();

        dslBuilder.append("//\n");
        dslBuilder.append("// GraphLiftingDataWorkflowRoutine\n");
        dslBuilder.append("//\n");

        dslBuilder.append("\n");

        dslBuilder.append("// get data from downstream node Filesystem\n");

        List<String> localDataIdList = new ArrayList<>();

        for (Map.Entry<String, List<String>> dataEntry : allData.entrySet()) {
            String downstreamNodeName = dataEntry.getKey();
            for (String dataName : dataEntry.getValue()) {
                String newDataName = node.getDataType() + node.getDataType() + "-" + dataName;
                Node downstreamNode = configurationRepository.getNode(downstreamNodeName);
                String downstreamNodeId = downstreamNode.getNodeId();
                dslBuilder.append("PROCESS FUNCTION LiftData(")
                        .append("\"NodeId=").append(downstreamNodeId).append("\", ")
                        .append("\"NodeStorage=").append(liftingStorage).append("\", ")
                        .append("\"DataId=").append(dataName).append("\", ")
                        .append(")")
                        .append(" RETURN ").append(newDataName).append(";\n");
                localDataIdList.add(newDataName);
            }
        }
        dslBuilder.append("\n");

        dslBuilder.append("// Put data to local cross-WorkflowRoutine storage ").append(saveStorage).append("\n");
        for (String dataId : localDataIdList) {
            dslBuilder.append("PUT DATA ")
                    .append(dataId)
                    .append(" TO STORAGE ").append(saveStorage)
                    .append(" MODIFIER local ")
                    .append("BY DATA ID ").append(dataId).append(";\n");
            storageLiftingDataStorage.put(dataId);
        }
        dslBuilder.append("\n");

        String dsl = dslBuilder.toString();

        nodeRepository.putNodeStorages(node.getNodeId(), storageLiftingDataStorage);

        return dsl;
    }
}
