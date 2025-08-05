package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.project_dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Project {

    @SerializedName("project_config_version")
    private String projectConfigVersion;

    @SerializedName("project_name")
    private String projectName;

    @SerializedName("structure")
    private ProjectNode structure;

    public Project() {
    }

    private Project(Builder builder) {
        projectConfigVersion = builder.projectConfigVersion;
        projectName = builder.projectName;
        structure = builder.structure;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Project copy) {
        Builder builder = new Builder();
        builder.projectConfigVersion = copy.getProjectConfigVersion();
        builder.projectName = copy.getProjectName();
        builder.structure = copy.getStructure();
        return builder;
    }

    public String getProjectConfigVersion() {
        return projectConfigVersion;
    }

    public String getProjectName() {
        return projectName;
    }

    public ProjectNode getStructure() {
        return structure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(projectConfigVersion, project.projectConfigVersion) && Objects.equals(projectName, project.projectName) && Objects.equals(structure, project.structure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectConfigVersion, projectName, structure);
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectConfigVersion='" + projectConfigVersion + '\'' +
                ", projectName='" + projectName + '\'' +
                ", structure=" + structure +
                '}';
    }

    /**
     * {@code Project} builder static inner class.
     */
    public static final class Builder {
        private String projectConfigVersion;
        private String projectName;
        private ProjectNode structure;

        private Builder() {
        }

        /**
         * Sets the {@code projectConfigVersion} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param projectConfigVersion the {@code projectConfigVersion} to set
         * @return a reference to this Builder
         */
        public Builder setProjectConfigVersion(String projectConfigVersion) {
            this.projectConfigVersion = projectConfigVersion;
            return this;
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
         * Sets the {@code node} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param structure the {@code node} to set
         * @return a reference to this Builder
         */
        public Builder setStructure(ProjectNode structure) {
            this.structure = structure;
            return this;
        }

        /**
         * Returns a {@code Project} built from the parameters previously set.
         *
         * @return a {@code Project} built with parameters of this {@code Project.Builder}
         */
        public Project build() {
            return new Project(this);
        }
    }
}