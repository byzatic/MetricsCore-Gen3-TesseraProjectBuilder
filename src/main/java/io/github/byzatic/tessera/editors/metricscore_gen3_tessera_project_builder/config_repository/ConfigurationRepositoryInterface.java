package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.PQLetaConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ConfigurationRepositoryInterface {
    @NotNull List<Node> getNodes();

    @NotNull List<PQLetaConfig> getPQLetaConfigs();

    @NotNull String getProjectVersion();

    @NotNull String getProjectName();

    @NotNull Node getNode(@NotNull String nodeName) throws OperationIncompleteException;
}
