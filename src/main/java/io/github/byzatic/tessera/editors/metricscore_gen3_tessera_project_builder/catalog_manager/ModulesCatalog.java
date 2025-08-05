package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import java.nio.file.Path;
import java.util.Objects;

public class ModulesCatalog {
    private String projectName = null;
    private Path catalog = null;
    private Path shared = null;
    private Path services = null;
    private Path workflowRoutines = null;

    public ModulesCatalog() {
    }

    private ModulesCatalog(Builder builder) {
        projectName = builder.projectName;
        catalog = builder.catalog;
        shared = builder.shared;
        services = builder.services;
        workflowRoutines = builder.workflowRoutines;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ModulesCatalog copy) {
        Builder builder = new Builder();
        builder.projectName = copy.getProjectName();
        builder.catalog = copy.getCatalog();
        builder.shared = copy.getShared();
        builder.services = copy.getServices();
        builder.workflowRoutines = copy.getWorkflowRoutines();
        return builder;
    }

    public String getProjectName() {
        return projectName;
    }

    public Path getCatalog() {
        return catalog;
    }

    public Path getShared() {
        return shared;
    }

    public Path getServices() {
        return services;
    }

    public Path getWorkflowRoutines() {
        return workflowRoutines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModulesCatalog that = (ModulesCatalog) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(catalog, that.catalog) && Objects.equals(shared, that.shared) && Objects.equals(services, that.services) && Objects.equals(workflowRoutines, that.workflowRoutines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, catalog, shared, services, workflowRoutines);
    }

    @Override
    public String toString() {
        return "ModulesCatalog{" +
                "projectName='" + projectName + '\'' +
                ", catalog=" + catalog +
                ", shared=" + shared +
                ", services=" + services +
                ", workflowRoutines=" + workflowRoutines +
                '}';
    }

    /**
     * {@code ModulesCatalog} builder static inner class.
     */
    public static final class Builder {
        private String projectName;
        private Path catalog;
        private Path shared;
        private Path services;
        private Path workflowRoutines;

        private Builder() {
        }

        /**
         * Sets the {@code projectName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param projectName the {@code projectName} to set
         * @return a reference to this Builder
         */
        public Builder setProjectName(String projectName) {
            this.projectName = projectName;
            return this;
        }

        /**
         * Sets the {@code catalog} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param catalog the {@code catalog} to set
         * @return a reference to this Builder
         */
        public Builder setCatalog(Path catalog) {
            this.catalog = catalog;
            return this;
        }

        /**
         * Sets the {@code shared} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param shared the {@code shared} to set
         * @return a reference to this Builder
         */
        public Builder setShared(Path shared) {
            this.shared = shared;
            return this;
        }

        /**
         * Sets the {@code services} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param services the {@code services} to set
         * @return a reference to this Builder
         */
        public Builder setServices(Path services) {
            this.services = services;
            return this;
        }

        /**
         * Sets the {@code workflowRoutines} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param workflowRoutines the {@code workflowRoutines} to set
         * @return a reference to this Builder
         */
        public Builder setWorkflowRoutines(Path workflowRoutines) {
            this.workflowRoutines = workflowRoutines;
            return this;
        }

        /**
         * Returns a {@code ModulesCatalog} built from the parameters previously set.
         *
         * @return a {@code ModulesCatalog} built with parameters of this {@code ModulesCatalog.Builder}
         */
        public ModulesCatalog build() {
            return new ModulesCatalog(this);
        }
    }
}
