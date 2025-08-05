package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;

public interface CatalogManagerInterface {
    ProjectCatalog makeProjectCatalog() throws OperationIncompleteException;

    NodeCatalog makeNodeCatalog(Node node) throws OperationIncompleteException;

    ProjectCatalog getProjectCatalog() throws OperationIncompleteException;
}
