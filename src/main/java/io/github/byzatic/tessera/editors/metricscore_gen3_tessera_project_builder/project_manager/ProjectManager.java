package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.ProjectCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto.ProjectGlobal;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.project_dto.Project;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.project_dto.ProjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ProjectManager implements ProjectManagerInterface {
    private final static Logger logger = LoggerFactory.getLogger(ProjectManager.class);
    private final CatalogManagerInterface catalogManager;
    private final ConfigurationRepositoryInterface configurationRepository;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final ProjectGlobalGenerator projectGlobalGenerator = new ProjectGlobalGenerator();

    public ProjectManager(CatalogManagerInterface catalogManager, ConfigurationRepositoryInterface configurationRepository) {
        this.catalogManager = catalogManager;
        this.configurationRepository = configurationRepository;
    }

    @Override
    public void create() throws OperationIncompleteException {
        try {
            generateProject();
        } catch (Exception e) {
            throw new OperationIncompleteException(e);
        }

    }

    private void generateProject() throws OperationIncompleteException {
        try {
            ProjectCatalog projectCatalog = catalogManager.getProjectCatalog();
            String projectName = configurationRepository.getProjectName();
            String projectVersion = configurationRepository.getProjectVersion();


            Project project = Project.newBuilder()
                    .setProjectName(projectName)
                    .setProjectConfigVersion(projectVersion)
                    .setStructure(getStructure())
                    .build();

            String projectFileName = "Project.json";
            try (Writer writer = new FileWriter(String.valueOf(projectCatalog.getDataCatalog().resolve(projectFileName)))) {
                gson.toJson(project, writer);
                logger.debug("Project.json saved");
            } catch (IOException ioe) {
                logger.error(projectFileName + " generation error: {}", ioe.getMessage(), ioe);
                throw new OperationIncompleteException(projectFileName + " generation error: " + ioe.getMessage(), ioe);
            }

            ProjectGlobal projectGlobal = projectGlobalGenerator.generate();

            String globalFileName = "Global.json";
            try (Writer writer = new FileWriter(String.valueOf(projectCatalog.getDataCatalog().resolve(globalFileName)))) {
                gson.toJson(projectGlobal, writer);
                logger.debug(globalFileName + " saved");
            } catch (IOException ioe) {
                logger.error(globalFileName + " generation error: {}", ioe.getMessage(), ioe);
                throw new OperationIncompleteException(globalFileName + " generation error: " + ioe.getMessage(), ioe);
            }


        } catch (Exception e) {
            throw new OperationIncompleteException(e);
        }
    }

    private ProjectNode getStructure() throws OperationIncompleteException {
        List<Node> nodeList = configurationRepository.getNodes();
        List<ProjectNode> projectRoots = NodeConverter.convert(nodeList);
        if (projectRoots.size() > 1) throw new OperationIncompleteException("More then one Root Node not supported.");
        if (projectRoots.isEmpty()) throw new OperationIncompleteException("Root Node not found.");
        return projectRoots.get(0);
    }

//    private Node getRoot(List<Node> nodeList) throws OperationIncompleteException {
//
//        // составляет мапу<нода, List<апстрим этой ноды>>
//        Map<Node, List<Node>> nodesUpstreams = new HashMap<>();
//        for (Node node : nodeList) {
//            for (String nodeName : node.getDownstream()) {
//                Node downstreamNode = configurationRepository.getNode(nodeName);
//                if (nodesUpstreams.containsKey(downstreamNode)) {
//                    List<Node> upstreamNodeList = nodesUpstreams.get(downstreamNode);
//                    if (upstreamNodeList.contains(node)) throw new OperationIncompleteException("Error...");
//                    upstreamNodeList.add(node);
//                    nodesUpstreams.put(downstreamNode, upstreamNodeList);
//                } else {
//                    List<Node> upstreamNodeList = new ArrayList<>();
//                    upstreamNodeList.add(node);
//                    nodesUpstreams.put(downstreamNode, upstreamNodeList);
//                }
//            }
//        }
//
//        Node rootNode = null;
//        for (Map.Entry<Node, List<Node>> nodeListEntry : nodesUpstreams.entrySet()) {
//            if (nodeListEntry.getValue().isEmpty()) {
//                if (rootNode != null) throw new OperationIncompleteException("Graph structure error; multiply roots");
//                rootNode= nodeListEntry.getKey();
//            }
//        }
//        return rootNode;
//    }
}
