package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder;

import io.github.byzatic.commons.TempDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Configuration {
    private final static Logger logger = LoggerFactory.getLogger(Configuration.class);
    public static final String APP_NAME = "mcg3-project-builder";
    public static final String APP_VERSION = "1.0";
    public static final Boolean ALLOW_RM_EXISTING_PROJECTS = Boolean.valueOf(System.getProperty("allowRmExistingProjects", "True"));
    public static final TempDirectory TEMP_DIRECTORY = new TempDirectory(Configuration.APP_NAME, Boolean.TRUE);
    public static final Path TEMP_OUTPUT_PATH = TEMP_DIRECTORY.getPath();
    public static final Path INPUT_FILE;
    public static final Path OUTPUT_FILE;

    static {
        String inputFile = System.getProperty("inputFile", null);
        String outputFile = System.getProperty("outputFile", null);
        if (inputFile == null) throw new RuntimeException("-DinputFile not set");
        if (outputFile == null) throw new RuntimeException("-DoutputFile not set");
        INPUT_FILE = Path.of(inputFile);
        OUTPUT_FILE = Path.of(outputFile);
    }


}
