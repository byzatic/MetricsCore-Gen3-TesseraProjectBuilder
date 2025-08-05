package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.DataNodeProcessor;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProcessingManager implements ProcessingManagerInterface {
    private final static Logger logger = LoggerFactory.getLogger(ProcessingManager.class);
    private final DataNodeProcessor dataNodeProcessor;

    public ProcessingManager(CatalogManagerInterface catalogManager, NodeRepositoryInterface nodeRepository, ConfigurationRepositoryInterface configurationRepository) {
        this.dataNodeProcessor = new DataNodeProcessor(catalogManager, nodeRepository, configurationRepository);
    }

    @Override
    public void process(Node node) throws OperationIncompleteException {

        switch (node.getNodeType()) {
            case "org_node":
                logger.debug("This is an organizational node.");
                dataNodeProcessor.create(node);
                break;
            case "data_node":
                logger.debug("This is a data node.");
                dataNodeProcessor.create(node);
                break;
            case "external_mc_exporter":
                logger.debug("This is an aggregation node.");
                break;
            default:
                OperationIncompleteException oie = new OperationIncompleteException("Unknown node type in node " + node);
                logger.error(oie.getMessage(), oie);
                throw oie;
        }
    }
}
