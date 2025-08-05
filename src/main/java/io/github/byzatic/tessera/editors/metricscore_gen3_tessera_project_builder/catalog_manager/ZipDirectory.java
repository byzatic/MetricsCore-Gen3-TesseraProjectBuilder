package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipDirectory implements ZipDirectoryInterface {
    private final static Logger logger = LoggerFactory.getLogger(ZipDirectory.class);

    @Override
    public void zipDirectory(Path sourceDirPath, Path zipFilePath) throws IOException {
        logger.debug("Zip directory {} to {}", sourceDirPath, zipFilePath);
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(zipFilePath));
             Stream<Path> paths = Files.walk(sourceDirPath)) {

            paths.filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString().replace("\\", "/"));
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            throw new UncheckedIOException(e);
                        }
                    });
            logger.debug("Zip complete");
        }
    }

    @Override
    public void moveZipToFile(Path zipFilePath, Path targetZipFilePath, boolean overwriteIfExists) throws IOException {
        logger.debug("Move archive {} to target {}", zipFilePath, targetZipFilePath);
        if (!Files.exists(zipFilePath)) {
            throw new IOException("ZIP file does not exist: " + zipFilePath);
        }

        Path targetDir = targetZipFilePath.getParent();
        if (targetDir != null && !Files.exists(targetDir)) {
            Files.createDirectories(targetDir);
        }

        if (Files.exists(targetZipFilePath)) {
            if (overwriteIfExists) {
                Files.delete(targetZipFilePath);
            } else {
                throw new FileAlreadyExistsException("Target ZIP already exists and overwrite is disabled: " + targetZipFilePath);
            }
        }

        Files.move(zipFilePath, targetZipFilePath, StandardCopyOption.REPLACE_EXISTING);
        logger.debug("Move complete");
    }
}
