package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.ZipDirectory;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.ZipDirectoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepository;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.graph_processor.GraphProcessor;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.graph_processor.GraphProcessorInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.module_manager.ModuleManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.PQLetaConfigManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.PQLetaConfigManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.ProcessingManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.ProcessingManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepository;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage.NodeRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.ProjectManager;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.ProjectManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Hello world!
 */
public class App {
    private final static Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            ConfigurationRepositoryInterface configurationRepository = new ConfigurationRepository(Configuration.INPUT_FILE);
            CatalogManagerInterface catalogManager = new CatalogManager(configurationRepository);

            PQLetaConfigManagerInterface pqLetaConfigManager = new PQLetaConfigManager(catalogManager, configurationRepository);
            pqLetaConfigManager.makePQLetaConfig();

            ProjectManagerInterface projectManager = new ProjectManager(catalogManager, configurationRepository);
            projectManager.create();

            NodeRepositoryInterface nodeRepository = new NodeRepository(configurationRepository);

            ProcessingManagerInterface processingManager = new ProcessingManager(catalogManager, nodeRepository, configurationRepository);

            GraphProcessorInterface graphProcessor = new GraphProcessor(configurationRepository, processingManager);
            graphProcessor.traverseFromLeaves();

            ModuleManager moduleManager = new ModuleManager(catalogManager);
            moduleManager.create();

            ZipDirectoryInterface zipDirectory = new ZipDirectory();
            Path sourceDirPath = catalogManager.getProjectCatalog().getCatalog();
            Path zipFilePath = Configuration.TEMP_OUTPUT_PATH.resolve(catalogManager.getProjectCatalog().getProjectName() + ".zip");
            zipDirectory.zipDirectory(sourceDirPath, zipFilePath);
            zipDirectory.moveZipToFile(zipFilePath, Configuration.OUTPUT_FILE, Boolean.TRUE);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
