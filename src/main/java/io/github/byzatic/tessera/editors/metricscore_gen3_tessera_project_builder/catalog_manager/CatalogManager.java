package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.Configuration;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CatalogManager implements CatalogManagerInterface {
    private final static Logger logger = LoggerFactory.getLogger(CatalogManager.class);
    private final Path outputCatalog = Configuration.TEMP_OUTPUT_PATH;
    private ProjectCatalog projectCatalog = null;
    private Path nodesCatalog = null;
    private ConfigurationRepositoryInterface configurationRepository = null;

    public CatalogManager(ConfigurationRepositoryInterface configurationRepository) throws OperationIncompleteException {
        this.configurationRepository = configurationRepository;
    }

    @Override
    public ProjectCatalog makeProjectCatalog() throws OperationIncompleteException {
        try {
            String projectName = configurationRepository.getProjectName();
            Path projectPath = outputCatalog.resolve(projectName);
            if (Files.exists(projectPath)) {
                logger.debug("projectPath is exists");
                logger.debug("Parameter allowRmExistingProjects is {}", Configuration.ALLOW_RM_EXISTING_PROJECTS);
                if (Configuration.ALLOW_RM_EXISTING_PROJECTS) {
                    DirectoryDeleter.deleteDirectory(projectPath);
                    logger.debug("projectPath removed");
                } else {
                    logger.error("projectPath can't be removed");
                    throw new OperationIncompleteException("Project " + projectName + " is already exists in output catalog" + outputCatalog);
                }
            }
            Files.createDirectory(projectPath);
            Path dataCatalog = projectPath.resolve("data");
            Files.createDirectory(dataCatalog);
            Path projectConfigurationFilesCatalog = projectPath.resolve("data").resolve("configuration_files");
            Files.createDirectory(projectConfigurationFilesCatalog);
            this.nodesCatalog = projectPath.resolve("data").resolve("nodes");
            Files.createDirectory(nodesCatalog);
            Path modulesCatalog = projectPath.resolve("modules");
            Files.createDirectory(modulesCatalog);
            Path servicesCatalog = projectPath.resolve("modules").resolve("services");
            Files.createDirectory(servicesCatalog);
            Path workflowRoutinesCatalog = projectPath.resolve("modules").resolve("workflow_routines");
            Files.createDirectory(workflowRoutinesCatalog);
            Path sharedCatalog = projectPath.resolve("modules").resolve("shared");
            Files.createDirectory(sharedCatalog);
            this.projectCatalog = ProjectCatalog.newBuilder()
                    .setProjectName(projectName)
                    .setCatalog(projectPath)
                    .setDataCatalog(dataCatalog)
                    .setConfigurationFilesCatalog(projectConfigurationFilesCatalog)
                    .setModulesCatalog(ModulesCatalog.newBuilder()
                            .setProjectName(projectName)
                            .setCatalog(modulesCatalog)
                            .setShared(sharedCatalog)
                            .setWorkflowRoutines(workflowRoutinesCatalog)
                            .setServices(servicesCatalog)
                            .build())
                    .build();
            return this.projectCatalog;
        } catch (IOException e) {
            throw new OperationIncompleteException(e);
        }
    }

    @Override
    public NodeCatalog makeNodeCatalog(Node node) throws OperationIncompleteException {
        try {
            if (nodesCatalog == null) makeProjectCatalog();
            Path nodeCatalog = nodesCatalog.resolve(node.getNodeFolderName());
            if (Files.exists(nodeCatalog))
                throw new OperationIncompleteException("Node folder " + nodeCatalog + " is already exists in output catalog" + outputCatalog);
            Files.createDirectory(nodeCatalog);
            Path nodeConfigurationFilesCatalog = nodeCatalog.resolve("configuration_files");
            Files.createDirectory(nodeConfigurationFilesCatalog);
            return NodeCatalog.newBuilder()
                    .setNodeName(node.getNodeName())
                    .setCatalog(nodeCatalog)
                    .setConfigurationFilesCatalog(nodeConfigurationFilesCatalog)
                    .build();
        } catch (IOException e) {
            throw new OperationIncompleteException(e);
        }
    }

    @Override
    public ProjectCatalog getProjectCatalog() throws OperationIncompleteException {
        if (this.projectCatalog == null) makeProjectCatalog();
        return this.projectCatalog;
    }
}
