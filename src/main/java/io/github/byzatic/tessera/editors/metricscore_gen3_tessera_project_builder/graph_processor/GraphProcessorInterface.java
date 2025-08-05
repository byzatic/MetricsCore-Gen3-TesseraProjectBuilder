package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.graph_processor;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;

public interface GraphProcessorInterface {
    void traverseFromLeaves() throws OperationIncompleteException;
}
