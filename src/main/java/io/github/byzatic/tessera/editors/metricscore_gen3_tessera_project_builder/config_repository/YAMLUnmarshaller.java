package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

class YAMLUnmarshaller {
    private final static Logger logger = LoggerFactory.getLogger(YAMLUnmarshaller.class);
    private final static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public static Config load(Path filePath) throws OperationIncompleteException {
        try {
            logger.debug("Loading project config from {}", filePath);
            Config config = mapper.readValue(filePath.toFile(), Config.class);
            logger.debug("Loaded project config");
            logger.trace("Loaded project config: {}", config);
            return config;
        } catch (Exception e) {
            logger.error("Config load exception", e);
            throw new OperationIncompleteException("Config load error: " + e.getMessage(), e);
        }
    }
}
