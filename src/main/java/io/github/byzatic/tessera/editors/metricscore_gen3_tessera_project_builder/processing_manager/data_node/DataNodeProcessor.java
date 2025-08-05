package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.PipelineGenerator;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.global.NodeGlobalManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataNodeProcessor {
    private final static Logger logger = LoggerFactory.getLogger(DataNodeProcessor.class);
    private final CatalogManagerInterface catalogManager;
    private final NodeGlobalManager nodeGlobalManager = new NodeGlobalManager();
    private final PipelineGenerator pipelineGenerator;

    public DataNodeProcessor(CatalogManagerInterface catalogManager, NodeRepositoryInterface nodeRepository, ConfigurationRepositoryInterface configurationRepository) {
        this.catalogManager = catalogManager;
        this.pipelineGenerator = new PipelineGenerator(nodeRepository, configurationRepository);
    }

    public void create(Node node) throws OperationIncompleteException {
        NodeCatalog nodeCatalog = catalogManager.makeNodeCatalog(node);
        nodeGlobalManager.create(nodeCatalog);
        pipelineGenerator.generate(nodeCatalog, node);
    }
}
