package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerOptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeStorageInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PrometheusGetDataWorkflowRoutine {
    private final SupportReasonMessageGenerator reasonMessageGenerator = new SupportReasonMessageGenerator();
    private final SupportVarNameGenerator varNameGenerator = new SupportVarNameGenerator();
    private final SupportDSL supportDSL = new SupportDSL();

    private final NodeRepositoryInterface nodeRepository;

    public PrometheusGetDataWorkflowRoutine(NodeRepositoryInterface nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public WorkerDescriptionItem create(NodeCatalog nodeCatalog, Node node) throws OperationIncompleteException {
        try {
            List<WorkerOptionItem> workerOptionItems = new ArrayList<>();

            WorkerOptionItem workerOptionItemPromqlConfigurationDAOfilePath = WorkerOptionItem.newBuilder()
                    .setKey("promqlConfigurationDAOfilePath")
                    .setValue("${PROJECT_GLOBAL_PATH}/PrometheusQueryConfigurations.json")
                    .build();
            workerOptionItems.add(workerOptionItemPromqlConfigurationDAOfilePath);

            WorkerOptionItem workerOptionItemPromqlTemplateFilePath = WorkerOptionItem.newBuilder()
                    .setKey("promqlTemplateFilePath")
                    .setValue("${PROJECT_GLOBAL_PATH}/PrometheusQueryTemplates.txt")
                    .build();
            workerOptionItems.add(workerOptionItemPromqlTemplateFilePath);

            WorkerOptionItem workerOptionItemDSLFile = WorkerOptionItem.newBuilder()
                    .setKey("MCg3-WorkflowRoutine-DSL-File")
                    .setValue("${NODE_PATH}/GetData-PrometheusGetDataWorkflowRoutine.mcg3dsl")
                    .build();
            workerOptionItems.add(workerOptionItemDSLFile);

            WorkerDescriptionItem workerDescriptionItem = WorkerDescriptionItem.newBuilder()
                    .setName("PrometheusGetDataWorkflowRoutine")
                    .setDescription("prometheus get data workflow routine")
                    .setOptions(workerOptionItems)
                    .build();

            String dsl = getDSL(node);
            Path dslFile = nodeCatalog.getConfigurationFilesCatalog().resolve("GetData-PrometheusGetDataWorkflowRoutine.mcg3dsl");
            supportDSL.writeDsl(dsl, dslFile);

            return workerDescriptionItem;
        } catch (Exception e) {
            throw new OperationIncompleteException(e);
        }
    }


    private String getDSL(Node node) {
        String pqletaId = node.getDataOrigin().get(0);
        String dataId = varNameGenerator.formatFirstAndLast(pqletaId);
        String saveStorage = "SELF_DATA_STORAGE";
        SupportReasonMessageGenerator.ReasonMessage reasonMessage = reasonMessageGenerator.generateReasonMessage(pqletaId);
        NodeStorageInterface storageSelfDataStorage = nodeRepository.getNodeStorage(node.getNodeId(), saveStorage);

        String dslBuilder = "//\n" +
                "// PrometheusGetDataWorkflowRoutine\n" +
                "//\n" +
                "\n" +
                "// get data with PQleta by ID\n" +
                "PROCESS FUNCTION GetData(\"PQletaQueryId=" + pqletaId + "\") RETURN " + dataId + ";\n" +
                "PROCESS FUNCTION RemoveServiceLabel(\"FromDataId=" + dataId + "\") RETURN " + dataId + ";\n" +
                "PROCESS FUNCTION ProcessReason(\n" +
                "\"DataId=" + dataId + "\",\n" +
                "\"PrometheusEmptyData=Prometheus have no data\",\n" +
                "\"PasteReasonWhenOk=True\",\n" +
                "\"OkReasonMessage=" + "Null" + "\",\n" +
                "\"WarningReasonMessage=" + reasonMessage.warningReasonMessage + "\",\n" +
                "\"AlarmReasonMessage=" + reasonMessage.alarmReasonMessage + "\",\n" +
                ") RETURN " + dataId + ";\n" +
                "\n" +
                "// put data to local cross-WorkflowRoutine storage\n" +
                "PUT DATA " + dataId +
                " TO STORAGE " + saveStorage +
                " MODIFIER local BY DATA ID " + dataId + ";";

        storageSelfDataStorage.put(dataId);
        nodeRepository.putNodeStorages(node.getNodeId(), storageSelfDataStorage);

        String dsl = dslBuilder;

        return dsl;
    }
}
