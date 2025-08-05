package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage;

public interface NodeRepositoryInterface {
    NodeStorageInterface getNodeStorage(String nodeName, String storageName);

    void putNodeStorages(String nodeName, NodeStorageInterface nodeStorage);
}
