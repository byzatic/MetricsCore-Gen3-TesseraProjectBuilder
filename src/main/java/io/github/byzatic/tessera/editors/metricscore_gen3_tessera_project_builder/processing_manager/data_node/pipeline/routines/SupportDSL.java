package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SupportDSL {
    private final static Logger logger = LoggerFactory.getLogger(SupportDSL.class);

    public void writeDsl(String content, Path filePath) throws OperationIncompleteException {
        try {
            // Если файл не существует, он будет создан
            Files.write(
                    filePath,
                    content.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE,      // Создать файл, если нет
                    StandardOpenOption.TRUNCATE_EXISTING // Очистить файл перед записью
            );
            logger.debug("Successfully written to " + filePath);
        } catch (IOException e) {
            throw new OperationIncompleteException(e);
        }
    }
}
