package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Config;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.PQLetaConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConfigurationRepository implements ConfigurationRepositoryInterface {
    private final static Logger logger = LoggerFactory.getLogger(ConfigurationRepository.class);
    private final Config config;

    public ConfigurationRepository(@NotNull Path configFilePath) throws OperationIncompleteException {
        Config rawConfig = YAMLUnmarshaller.load(configFilePath);
        this.config = raiseRoot(rawConfig);
    }

    @Override
    public @NotNull List<Node> getNodes() {
        return config.getNodes();
    }

    @Override
    public @NotNull List<PQLetaConfig> getPQLetaConfigs() {
        return config.getPqletaConfigs();
    }

    @Override
    public @NotNull String getProjectVersion() {
        return config.getProjectVersion();
    }

    @Override
    public @NotNull String getProjectName() {
        return config.getProjectName();
    }

    @Override
    public @NotNull Node getNode(@NotNull String nodeId) throws OperationIncompleteException {
        Node result = null;
        for (Node node : config.getNodes()) {
            if (node.getNodeId().equals(nodeId)) result = node;
        }
        if (result == null) {
            OperationIncompleteException oie = new OperationIncompleteException("Node with Id " + nodeId + " was not found");
            logger.error(oie.getMessage(), oie);
            logger.error("Nodes list size {}", config.getNodes().size());
            for (Node node : config.getNodes()) {
                logger.error("Id {} node {}", node.getNodeId(), node);
            }
            throw oie;
        }
        return result;
    }

    private Config raiseRoot(Config config) throws OperationIncompleteException {
        List<Node> roots = findRoots(config.getNodes());
        if (roots.size() > 1) throw new OperationIncompleteException("Multiply roots found!");
        String rootNodeName = roots.get(0).getNodeId();
        Config.Builder newConfig = Config.newBuilder(config);
        List<Node> newNodeList = new ArrayList<>();
        for (Node node : config.getNodes()) {
            Boolean isRoot = Boolean.FALSE;
            if (node.getNodeId().equals(rootNodeName)) {
                isRoot = Boolean.TRUE;
                logger.debug("Root Node is {}", node);
            }
            Node newNode = Node.newBuilder(node).setIsRoot(isRoot).build();
            newNodeList.add(newNode);
        }
        return newConfig.setNodes(newNodeList).build();
    }

    private List<Node> findRoots(List<Node> allNodes) {
        Set<String> allNodeNames = allNodes.stream()
                .map(Node::getNodeId)
                .collect(Collectors.toSet());

        Set<String> referencedNodes = allNodes.stream()
                .filter(node -> node.getDownstream() != null)
                .flatMap(node -> node.getDownstream().stream())
                .collect(Collectors.toSet());

        Set<String> rootNames = new HashSet<>(allNodeNames);
        rootNames.removeAll(referencedNodes);

        return allNodes.stream()
                .filter(node -> rootNames.contains(node.getNodeId()))
                .collect(Collectors.toList());
    }
}
