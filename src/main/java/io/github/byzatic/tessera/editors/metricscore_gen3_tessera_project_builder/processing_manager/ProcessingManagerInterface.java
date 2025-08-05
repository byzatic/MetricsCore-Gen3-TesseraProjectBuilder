package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;

public interface ProcessingManagerInterface {
    void process(Node node) throws OperationIncompleteException;
}
