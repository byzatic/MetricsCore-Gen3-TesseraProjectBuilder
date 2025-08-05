package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage;

import java.util.List;

public interface NodeStorageInterface {
    List<String> list();

    void put(String dataId);

    String getStorageName();
}
