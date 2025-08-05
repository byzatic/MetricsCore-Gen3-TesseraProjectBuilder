package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.external_mc_exporter.pipeline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.DataEnrichmentWorkflowRoutine;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.GraphLiftingDataWorkflowRoutine;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.ProcessingStatusWorkflowRoutine;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines.PrometheusGetDataWorkflowRoutine;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.Pipeline;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.StagesConsistencyItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.StagesDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto.WorkerDescriptionItem;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PipelineGenerator {
    private final static Logger logger = LoggerFactory.getLogger(PipelineGenerator.class);

    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    private final PrometheusGetDataWorkflowRoutine prometheusGetDataWorkflowRoutine;
    private final ProcessingStatusWorkflowRoutine processingStatusWorkflowRoutine;
    private final GraphLiftingDataWorkflowRoutine graphLiftingDataWorkflowRoutine;
    private final DataEnrichmentWorkflowRoutine dataEnrichmentWorkflowRoutine;

    public PipelineGenerator(NodeRepositoryInterface nodeRepository, ConfigurationRepositoryInterface configurationRepository) {
        this.prometheusGetDataWorkflowRoutine = new PrometheusGetDataWorkflowRoutine(nodeRepository);
        this.graphLiftingDataWorkflowRoutine = new GraphLiftingDataWorkflowRoutine(nodeRepository, configurationRepository);
        this.processingStatusWorkflowRoutine = new ProcessingStatusWorkflowRoutine(nodeRepository);
        this.dataEnrichmentWorkflowRoutine = new DataEnrichmentWorkflowRoutine(nodeRepository);
    }

    public void generate(NodeCatalog nodeCatalog, Node node) throws OperationIncompleteException {
        try {
            Pipeline pipeline = Pipeline.newBuilder()
                    .setStagesConsistency(getStagesConsistency(node))
                    .setStagesDescription(getStagesDescription(node, nodeCatalog))
                    .build();

            String projectFileName = "pipeline.json";
            try (Writer writer = new FileWriter(String.valueOf(nodeCatalog.getCatalog().resolve(projectFileName)))) {
                gson.toJson(pipeline, writer);
                logger.debug("pipeline.json saved");
            } catch (IOException ioe) {
                logger.error(projectFileName + " generation error: {}", ioe.getMessage(), ioe);
                throw new OperationIncompleteException(projectFileName + " generation error: " + ioe.getMessage(), ioe);
            }

        } catch (Exception e) {
            throw new OperationIncompleteException(e);
        }
    }

    private List<StagesConsistencyItem> getStagesConsistency(Node node) throws OperationIncompleteException {
        List<StagesConsistencyItem> stagesConsistencyItems = new ArrayList<>();

        if (node.getDataOrigin().isEmpty()) {
            throw new OperationIncompleteException("Node " + node + " have empty Origin");
        }
        if (!node.getDownstream().isEmpty()) {
            throw new OperationIncompleteException("Node " + node + " have Downstream: NotImplemented");
        }

        StagesConsistencyItem stagesConsistencyItemLiftingData = StagesConsistencyItem.newBuilder()
                .setPosition(1)
                .setStageId("GetData")
                .build();
        stagesConsistencyItems.add(stagesConsistencyItemLiftingData);

        StagesConsistencyItem stagesConsistencyItemProcessingStatus = StagesConsistencyItem.newBuilder()
                .setPosition(2)
                .setStageId("ProcessingStatus")
                .build();
        stagesConsistencyItems.add(stagesConsistencyItemProcessingStatus);

        StagesConsistencyItem stagesConsistencyItemDataEnrichment = StagesConsistencyItem.newBuilder()
                .setPosition(3)
                .setStageId("DataEnrichment")
                .build();
        stagesConsistencyItems.add(stagesConsistencyItemDataEnrichment);

        return stagesConsistencyItems;
    }

    private List<StagesDescriptionItem> getStagesDescription(Node node, NodeCatalog nodeCatalog) throws OperationIncompleteException {
        List<StagesDescriptionItem> stagesDescriptionItems = new ArrayList<>();

        if (node.getDataOrigin().isEmpty()) {
            throw new OperationIncompleteException("Node " + node + " have empty Origin");
        }
        if (!node.getDownstream().isEmpty()) {
            throw new OperationIncompleteException("Node " + node + " have Downstream: NotImplemented");
        }

        // GetData
        stagesDescriptionItems.add(genGetDataStage(node, nodeCatalog));

        // ProcessingStatus
        stagesDescriptionItems.add(genProcessingStatusStage(node, nodeCatalog));

        // DataEnrichment
        stagesDescriptionItems.add(genDataEnrichmentStage(node, nodeCatalog));

        return stagesDescriptionItems;
    }

    private StagesDescriptionItem genGetDataStage(Node node, NodeCatalog nodeCatalog) throws OperationIncompleteException {
        List<WorkerDescriptionItem> workerDescriptionItems = new ArrayList<>();
        WorkerDescriptionItem workflowRoutine = prometheusGetDataWorkflowRoutine.create(nodeCatalog, node);
        workerDescriptionItems.add(workflowRoutine);
        StagesDescriptionItem stagesDescriptionItem = StagesDescriptionItem.newBuilder()
                .setStageId("GetData")
                .setWorkersDescription(workerDescriptionItems)
                .build();
        return stagesDescriptionItem;
    }

    private StagesDescriptionItem genProcessingStatusStage(Node node, NodeCatalog nodeCatalog) {
        List<WorkerDescriptionItem> workerDescriptionItems = new ArrayList<>();
        WorkerDescriptionItem workflowRoutine = processingStatusWorkflowRoutine.create(nodeCatalog, node);
        workerDescriptionItems.add(workflowRoutine);
        StagesDescriptionItem stagesDescriptionItem = StagesDescriptionItem.newBuilder()
                .setStageId("ProcessingStatus")
                .setWorkersDescription(workerDescriptionItems)
                .build();
        return stagesDescriptionItem;
    }

    private StagesDescriptionItem genDataEnrichmentStage(Node node, NodeCatalog nodeCatalog) {
        List<WorkerDescriptionItem> workerDescriptionItems = new ArrayList<>();
        WorkerDescriptionItem workflowRoutine = dataEnrichmentWorkflowRoutine.create(nodeCatalog, node);
        workerDescriptionItems.add(workflowRoutine);
        StagesDescriptionItem stagesDescriptionItem = StagesDescriptionItem.newBuilder()
                .setStageId("DataEnrichment")
                .setWorkersDescription(workerDescriptionItems)
                .build();
        return stagesDescriptionItem;
    }
}
