package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage;

import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class NodeRepository implements NodeRepositoryInterface {
    private final static Logger logger = LoggerFactory.getLogger(NodeRepository.class);

    private final Map<String, Map<String, NodeStorageInterface>> repository = new HashMap<>();

    public NodeRepository(ConfigurationRepositoryInterface configurationRepository) {
        for (Node node : configurationRepository.getNodes()) {
            repository.put(node.getNodeId(), new HashMap<>());
        }
    }

    @Override
    public @NotNull NodeStorageInterface getNodeStorage(@NotNull String nodeName, @NotNull String storageName) {
        logger.debug("Get: node={} storage={}", nodeName, storageName);
        isNodeExists(nodeName);
        Map<String, NodeStorageInterface> nodeStorageMap = repository.get(nodeName);
        NodeStorageInterface result;
        if (nodeStorageMap.containsKey(storageName)) {
            result = nodeStorageMap.get(storageName);
            logger.debug("Node storage exists; nodeStorage={}", result);
        } else {
            NodeStorageInterface nodeStorage = new NodeStorage(storageName);
            nodeStorageMap.put(storageName, nodeStorage);
            result = nodeStorage;
            logger.debug("Node storage not exists; create nodeStorage={}", result);
        }
        return result;
    }

    @Override
    public void putNodeStorages(String nodeName, NodeStorageInterface nodeStorage) {
        logger.debug("Put: node={} nodeStorage={}", nodeName, nodeStorage);
        isNodeExists(nodeName);
        Map<String, NodeStorageInterface> nodeStorageMap = repository.get(nodeName);
        nodeStorageMap.put(nodeStorage.getStorageName(), nodeStorage);
    }

    private void isNodeExists(String nodeName) {
        if (!repository.containsKey(nodeName))
            throw new IllegalArgumentException("Node with name " + nodeName + " not exists.");
    }
}
