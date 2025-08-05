package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.graph_processor;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.ConfigurationRepositoryInterface;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto.Node;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.ProcessingManagerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphProcessor implements GraphProcessorInterface {
    private final static Logger logger = LoggerFactory.getLogger(GraphProcessor.class);
    private final Map<String, Node> nodeMap;
    private final Set<String> processed = new HashSet<>();
    private final ProcessingManagerInterface processingManager;

    public GraphProcessor(ConfigurationRepositoryInterface configurationRepository, ProcessingManagerInterface processingManager) {
        this.nodeMap = new HashMap<>();
        this.processingManager = processingManager;
        for (Node node : configurationRepository.getNodes()) {
            nodeMap.put(node.getNodeId(), node);
        }
    }

    @Override
    public void traverseFromLeaves() throws OperationIncompleteException {
        // пока есть необработанные ноды, пытаемся обработать
        boolean progress;
        do {
            progress = false;
            for (Node node : nodeMap.values()) {
                String nodeName = node.getNodeId();
                if (!processed.contains(nodeName) && canProcess(node)) {
                    process(node);
                    processed.add(nodeName);
                    progress = true;
                }
            }
            // если за проход не обработали ни одной ноды — значит, есть цикл или ошибка в графе
            if (!progress) {
                Set<String> unprocessed = new HashSet<>(nodeMap.keySet());
                unprocessed.removeAll(processed);
                if (!unprocessed.isEmpty()) {
                    throw new IllegalStateException("Cycle detected or unreachable nodes: " + unprocessed);
                }
            }
        } while (processed.size() < nodeMap.size());
    }

    private boolean canProcess(Node node) {
        if (node.getDownstream() == null || node.getDownstream().isEmpty()) {
            return true;
        }
        for (String downstreamName : node.getDownstream()) {
            if (!processed.contains(downstreamName)) {
                return false;
            }
        }
        return true;
    }

    private void process(Node node) throws OperationIncompleteException {
        logger.debug("Processing: " + node.getNodeId());
        // Здесь твоя логика обработки
        processingManager.process(node);
    }
}
