package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.global;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.NodeCatalog;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.global.dto.NodeGlobal;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.global.dto.StorageItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class NodeGlobalManager {
    private final static Logger logger = LoggerFactory.getLogger(NodeGlobalManager.class);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

    public NodeGlobalManager() {
    }

    public void create(NodeCatalog nodeCatalog) throws OperationIncompleteException {
        List<StorageItem> storageItems = new ArrayList<>();

        StorageItem storageItemLiftingDataStorage = StorageItem.newBuilder()
                .setIdName("LIFTING_DATA_STORAGE")
                .setDescription("Storage for lifted data")
                .setOptions(new ArrayList<>())
                .build();
        storageItems.add(storageItemLiftingDataStorage);

        StorageItem storageItemSelfDataStorage = StorageItem.newBuilder()
                .setIdName("SELF_DATA_STORAGE")
                .setDescription("Storage for self data")
                .setOptions(new ArrayList<>())
                .build();
        storageItems.add(storageItemSelfDataStorage);

        StorageItem storageItemPublicDataStorage = StorageItem.newBuilder()
                .setIdName("PUBLIC_DATA_STORAGE")
                .setDescription("Storage for upstreams")
                .setOptions(new ArrayList<>())
                .build();
        storageItems.add(storageItemPublicDataStorage);

        NodeGlobal nodeGlobal = NodeGlobal.newBuilder()
                .setStorages(storageItems)
                .build();

        String fileName = "global.json";
        try (Writer writer = new FileWriter(String.valueOf(nodeCatalog.getCatalog().resolve(fileName)))) {
            gson.toJson(nodeGlobal, writer);
            logger.debug("global.json saved");
        } catch (IOException ioe) {
            logger.error(fileName + " generation error: {}", ioe.getMessage(), ioe);
            throw new OperationIncompleteException(fileName + " generation error: " + ioe.getMessage(), ioe);
        }

    }
}
