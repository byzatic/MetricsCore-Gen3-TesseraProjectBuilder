package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.project_dto.ProjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class NodeConverter {
    private final static Logger logger = LoggerFactory.getLogger(NodeConverter.class);

    public NodeConverter() {
    }

    public static List<ProjectNode> convert(List<Node> oldNodes) {
        Map<String, Node> nodeMap = oldNodes.stream()
                .collect(Collectors.toMap(
                        node -> node.getNodeId(),
                        node -> node
                ));

        // для всех узлов
        //List<ProjectNode> projectNodeList = oldNodes.stream()
        //        .map(oldNode -> toProjectNode(oldNode, nodeMap))
        //        .toList();


        // только для корней
        List<Node> rootNodes = findRoots(oldNodes);
        for (Node rootNode : rootNodes) {
            logger.debug("found root -> {}", rootNode);
        }
        List<ProjectNode> projectNodeList = rootNodes.stream()
                .map(rootNode -> toProjectNode(rootNode, nodeMap))
                .toList();

        for (ProjectNode projectNode : projectNodeList) {
            logger.debug("created structure -> {}", projectNode);
        }

        return projectNodeList;
    }

    private static List<Node> findRoots(List<Node> allNodes) {
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

    private static ProjectNode toProjectNode(Node oldNode, Map<String, Node> nodeMap) {
        ProjectNode.Builder newNode = ProjectNode.newBuilder();
        newNode.setName(oldNode.getNodeName());
        newNode.setId(oldNode.getNodeId());
        newNode.setDescription("");

        if (oldNode.getDownstream() != null) {
            List<ProjectNode> downstreamNodes = oldNode.getDownstream().stream()
                    .map(downstreamStr -> {
                        Node downstreamOld = nodeMap.get(downstreamStr);
                        if (downstreamOld != null) {
                            return toProjectNode(downstreamOld, nodeMap);
                        } else {
                            IllegalStateException ise = new IllegalStateException("Downstream refers to a non-existent Node " + downstreamStr);
                            logger.error(ise.getMessage(), ise);
                            logger.error("Data trace: downstreamStr {}", downstreamStr);
                            logger.error("Data trace: downstreamOld {}", downstreamOld);
                            logger.error("Data trace: oldNode {}", oldNode);
                            for (Map.Entry<String, Node> nodeEntry : nodeMap.entrySet()) {
                                logger.error("Data trace nodeMap: {} - {}", nodeEntry.getKey(), nodeEntry.getValue());
                            }
                            throw ise;
                        }
                    })
                    .collect(Collectors.toList());
            newNode.setDownstream(downstreamNodes);
        }

        return newNode.build();
    }
}
