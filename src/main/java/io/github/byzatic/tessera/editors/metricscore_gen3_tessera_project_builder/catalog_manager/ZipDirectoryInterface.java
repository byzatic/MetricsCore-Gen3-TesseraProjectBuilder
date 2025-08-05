package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import java.io.IOException;
import java.nio.file.Path;

public interface ZipDirectoryInterface {
    void zipDirectory(Path sourceDirPath, Path zipFilePath) throws IOException;

    void moveZipToFile(Path zipFilePath, Path targetZipFilePath, boolean overwriteIfExists) throws IOException;

}
