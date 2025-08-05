package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.storage;

import io.github.byzatic.commons.ObjectsUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NodeStorage implements NodeStorageInterface {
    private final String storageName;
    private final List<String> stringList = new ArrayList<>();

    public NodeStorage(@NotNull String storageName) {
        ObjectsUtils.requireNonNull(storageName, new IllegalArgumentException("Storage Name must be NotNull"));
        this.storageName = storageName;
    }

    @Override
    public @NotNull List<String> list() {
        return stringList;
    }

    @Override
    public void put(@NotNull String dataId) {
        if (stringList.contains(dataId))
            throw new IllegalArgumentException("dataId " + dataId + " already exists in storage " + storageName);
        stringList.add(dataId);
    }

    @Override
    public @NotNull String getStorageName() {
        return storageName;
    }

    @Override
    public String toString() {
        return "NodeStorage{" +
                "storageName='" + storageName + '\'' +
                ", stringList=" + stringList +
                '}';
    }
}
