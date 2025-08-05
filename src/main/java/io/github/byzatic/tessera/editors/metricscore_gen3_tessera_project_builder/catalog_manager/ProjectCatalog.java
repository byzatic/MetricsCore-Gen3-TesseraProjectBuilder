package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import java.nio.file.Path;
import java.util.Objects;

public class ProjectCatalog {
    private String projectName = null;
    private Path catalog = null;
    private Path configurationFilesCatalog = null;
    private ModulesCatalog modulesCatalog = null;
    private Path dataCatalog = null;

    public ProjectCatalog() {
    }

    private ProjectCatalog(Builder builder) {
        projectName = builder.projectName;
        catalog = builder.catalog;
        configurationFilesCatalog = builder.configurationFilesCatalog;
        modulesCatalog = builder.modulesCatalog;
        dataCatalog = builder.dataCatalog;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProjectCatalog copy) {
        Builder builder = new Builder();
        builder.projectName = copy.getProjectName();
        builder.catalog = copy.getCatalog();
        builder.configurationFilesCatalog = copy.getConfigurationFilesCatalog();
        builder.modulesCatalog = copy.getModulesCatalog();
        builder.dataCatalog = copy.getDataCatalog();
        return builder;
    }

    public String getProjectName() {
        return projectName;
    }

    public Path getCatalog() {
        return catalog;
    }

    public Path getConfigurationFilesCatalog() {
        return configurationFilesCatalog;
    }

    public ModulesCatalog getModulesCatalog() {
        return modulesCatalog;
    }

    public Path getDataCatalog() {
        return dataCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectCatalog that = (ProjectCatalog) o;
        return Objects.equals(projectName, that.projectName) && Objects.equals(catalog, that.catalog) && Objects.equals(configurationFilesCatalog, that.configurationFilesCatalog) && Objects.equals(modulesCatalog, that.modulesCatalog) && Objects.equals(dataCatalog, that.dataCatalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, catalog, configurationFilesCatalog, modulesCatalog, dataCatalog);
    }

    @Override
    public String toString() {
        return "ProjectCatalog{" +
                "projectName='" + projectName + '\'' +
                ", catalog=" + catalog +
                ", configurationFilesCatalog=" + configurationFilesCatalog +
                ", modulesCatalog=" + modulesCatalog +
                ", dataCatalog=" + dataCatalog +
                '}';
    }

    /**
     * {@code ProjectCatalog} builder static inner class.
     */
    public static final class Builder {
        private String projectName;
        private Path catalog;
        private Path configurationFilesCatalog;
        private ModulesCatalog modulesCatalog;
        private Path dataCatalog;

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
         * Sets the {@code configurationFilesCatalog} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param configurationFilesCatalog the {@code configurationFilesCatalog} to set
         * @return a reference to this Builder
         */
        public Builder setConfigurationFilesCatalog(Path configurationFilesCatalog) {
            this.configurationFilesCatalog = configurationFilesCatalog;
            return this;
        }

        /**
         * Sets the {@code modulesCatalog} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param modulesCatalog the {@code modulesCatalog} to set
         * @return a reference to this Builder
         */
        public Builder setModulesCatalog(ModulesCatalog modulesCatalog) {
            this.modulesCatalog = modulesCatalog;
            return this;
        }

        /**
         * Sets the {@code dataCatalog} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dataCatalog the {@code dataCatalog} to set
         * @return a reference to this Builder
         */
        public Builder setDataCatalog(Path dataCatalog) {
            this.dataCatalog = dataCatalog;
            return this;
        }

        /**
         * Returns a {@code ProjectCatalog} built from the parameters previously set.
         *
         * @return a {@code ProjectCatalog} built with parameters of this {@code ProjectCatalog.Builder}
         */
        public ProjectCatalog build() {
            return new ProjectCatalog(this);
        }
    }
}
