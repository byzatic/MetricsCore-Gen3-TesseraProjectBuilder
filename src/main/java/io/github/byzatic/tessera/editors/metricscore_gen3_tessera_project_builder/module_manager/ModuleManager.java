package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.module_manager;

import io.github.byzatic.commons.base_exceptions.OperationIncompleteException;
import io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager.CatalogManagerInterface;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ModuleManager {
    private final CatalogManagerInterface catalogManager;

    public ModuleManager(CatalogManagerInterface catalogManager) {
        this.catalogManager = catalogManager;
    }

    public void create() throws OperationIncompleteException {
        Map<String, List<String>> modulesScopeMap = new HashMap<>();

        modulesScopeMap.put("services", Collections.singletonList("modules/services/service-prometheus-export-0.0.1-jar-with-dependencies.jar"));
        modulesScopeMap.put("shared", Collections.singletonList("modules/shared/sharedresources-project-common-0.0.1-jar-with-dependencies.jar"));
        List<String> modules = new ArrayList<>();
        modules.add("modules/workflow_routines/workflowroutine-data-enrichment-0.0.1-jar-with-dependencies.jar");
        modules.add("modules/workflow_routines/workflowroutine-graph-lifting-data-0.0.1-jar-with-dependencies.jar");
        modules.add("modules/workflow_routines/workflowroutine-processing-status-0.0.1-jar-with-dependencies.jar");
        modules.add("modules/workflow_routines/workflowroutine-prometheus-get-data-0.0.1-jar-with-dependencies.jar");
        modulesScopeMap.put("workflow_routines", modules);

        for (Map.Entry<String, List<String>> moduleScope : modulesScopeMap.entrySet()) {
            crateModule(moduleScope);
        }
    }


    private void crateModule(Map.Entry<String, List<String>> moduleScope) {
        String moduleType = moduleScope.getKey();
        for (String module : moduleScope.getValue()) {
            try (InputStream moduleFile = ModuleManager.class.getClassLoader().getResourceAsStream(module)) {

                String fileName = Paths.get(module).getFileName().toString();

                // SHIT CODDING!
                Path targetPath = null;
                if (moduleType.equals("services"))
                    targetPath = catalogManager.getProjectCatalog().getModulesCatalog().getServices().resolve(fileName);
                if (moduleType.equals("shared"))
                    targetPath = catalogManager.getProjectCatalog().getModulesCatalog().getShared().resolve(fileName);
                if (moduleType.equals("workflow_routines"))
                    targetPath = catalogManager.getProjectCatalog().getModulesCatalog().getWorkflowRoutines().resolve(fileName);

                if (moduleFile == null)
                    throw new OperationIncompleteException("Internal error: " + fileName + " was not found");

                try (OutputStream os = new FileOutputStream(String.valueOf(targetPath))) {
                    byte[] buffer = new byte[8192];
                    int length;
                    while ((length = moduleFile.read(buffer)) > 0) {
                        os.write(buffer, 0, length);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
