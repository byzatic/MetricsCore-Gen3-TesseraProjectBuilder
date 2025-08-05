package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.data_node.pipeline.routines;

public class SupportVarNameGenerator {

    public String formatFirstAndLast(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        String[] parts = input.trim().split("\\s+");
        if (parts.length < 2) {
            return null; // Недостаточно слов
        }

        String first = parts[0].toUpperCase();
        String last = parts[parts.length - 1].toUpperCase();

        return first + last;
    }
}
