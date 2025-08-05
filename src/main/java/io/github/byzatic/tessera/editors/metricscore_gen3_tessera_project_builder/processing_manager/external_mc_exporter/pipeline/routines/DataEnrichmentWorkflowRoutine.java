package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.external_mc_exporter.pipeline.routines;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.SupportDSL;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerOptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeStorageInterface;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataEnrichmentWorkflowRoutine {
    private final NodeRepositoryInterface nodeRepository;

    private final SupportDSL supportDSL = new SupportDSL();

    public DataEnrichmentWorkflowRoutine(NodeRepositoryInterface nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public WorkerDescriptionItem create(NodeCatalog nodeCatalog, Node node) {
        try {
            List<WorkerOptionItem> workerOptionItems = new ArrayList<>();
            String dslFileName = "DataEnrichment-DataEnrichmentWorkflowRoutine.mcg3dsl";

            WorkerOptionItem workerOptionItemDSLFile = WorkerOptionItem.newBuilder()
                    .setKey("MCg3-WorkflowRoutine-DSL-File")
                    .setValue("${NODE_PATH}/" + dslFileName)
                    .build();
            workerOptionItems.add(workerOptionItemDSLFile);

            WorkerDescriptionItem workerDescriptionItem = WorkerDescriptionItem.newBuilder()
                    .setName("DataEnrichmentWorkflowRoutine")
                    .setDescription("data enrichment workflow routine")
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

    public static boolean endsWithStatusRef(String input) {
        return input != null && input.endsWith("-StatusRef");
    }

    private String getDSL(Node node) {
        String saveStorageName = "PUBLIC_DATA_STORAGE";
        String uploadPrometheusStorageName = "PROMETHEUS_DATA_STORAGE";

        String selfStorageName = "SELF_DATA_STORAGE";
        String liftingStorageName = "LIFTING_DATA_STORAGE";

        NodeStorageInterface saveStorage = nodeRepository.getNodeStorage(node.getNodeId(), saveStorageName);

        NodeStorageInterface storageSELF = nodeRepository.getNodeStorage(node.getNodeId(), selfStorageName);
        NodeStorageInterface storageLIFTING = nodeRepository.getNodeStorage(node.getNodeId(), liftingStorageName);

        List<String> statuses = new ArrayList<>();
        List<String> liftings = new ArrayList<>();

        StringBuilder dslBuilder = new StringBuilder();

        dslBuilder.append("//\n");
        dslBuilder.append("// DataEnrichmentWorkflowRoutine\n");
        dslBuilder.append("//\n");
        dslBuilder.append("\n");

        dslBuilder.append("// get data\n");
        for (String data : storageLIFTING.list()) {
            dslBuilder.append("GET FROM SELF STORAGE ").append(storageLIFTING.getStorageName()).append(" BY DATA ID ").append(data).append(";\n");
            liftings.add(data);
        }
        for (String data : storageSELF.list()) {
            if (endsWithStatusRef(data)) {
                dslBuilder.append("GET FROM SELF STORAGE ").append(storageSELF.getStorageName()).append(" BY DATA ID ").append(data).append(";\n");
                statuses.add(data);
            }
        }
        dslBuilder.append("\n");

        for (String lifting : liftings) {
            dslBuilder.append("// process function AddLabel\n");
            dslBuilder.append("PROCESS FUNCTION AddLabel(\n");
            dslBuilder.append("\"DataId=").append(lifting).append("\",\n");
            dslBuilder.append("\"PromLabel_").append(node.getDataType()).append("=").append(node.getDataName()).append("\"\n");
            dslBuilder.append(") RETURN ").append(lifting).append(";\n");
            dslBuilder.append("\n");
        }
        for (String status : statuses) {
            dslBuilder.append("// process function AddGraphPath\n");
            dslBuilder.append("PROCESS FUNCTION AddGraphPath(\n");
            dslBuilder.append("\"DataId=").append(status).append("\"\n");
            dslBuilder.append(") RETURN ").append(status).append(";\n");
            dslBuilder.append("\n");
        }

        dslBuilder.append("// Put data to local cross-pipeline storage PUBLIC_DATA_STORAGE\n");
        List<String> dataList = new ArrayList<>(liftings);
        dataList.addAll(statuses);
        for (String data : dataList) {
            dslBuilder.append("PUT DATA ").append(data).append(" TO STORAGE ").append(saveStorage.getStorageName()).append(" MODIFIER local BY DATA ID ").append(data).append(";\n");
            saveStorage.put(data);
        }
        dslBuilder.append("\n");

        if (node.isRoot()) {
            dslBuilder.append("// Put data to global storage PROMETHEUS_DATA_STORAGE\n");
            for (String data : dataList) {
                dslBuilder.append("PUT DATA ").append(data).append(" TO STORAGE ").append(uploadPrometheusStorageName).append(" MODIFIER global BY DATA ID ").append(data).append(";\n");
            }
            dslBuilder.append("\n");
        }

        String dsl = dslBuilder.toString();

        nodeRepository.putNodeStorages(node.getNodeId(), saveStorage);

        return dsl;
    }
}
