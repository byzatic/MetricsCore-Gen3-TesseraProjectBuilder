package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.byzatic.commons.ObjectsUtils;

import java.util.List;
import java.util.Objects;

public class Config {
    @JsonProperty("project_version")
    private String projectVersion = null;
    @JsonProperty("project_name")
    private String projectName = null;
    @JsonProperty("pqleta_config")
    private List<PQLetaConfig> pqletaConfigs = null;
    @JsonProperty("nodes")
    private List<Node> nodes = null;

    public Config() {
    }

    private Config(Builder builder) {
        ObjectsUtils.requireNonNull(builder.projectVersion, new IllegalArgumentException("projectVersion must be set"));
        this.projectVersion = builder.projectVersion;

        ObjectsUtils.requireNonNull(builder.projectName, new IllegalArgumentException("projectName must be set"));
        this.projectName = builder.projectName;

        ObjectsUtils.requireNonNull(builder.pqletaConfigs, new IllegalArgumentException("pqletaConfigs must be set"));
        this.pqletaConfigs = builder.pqletaConfigs;

        ObjectsUtils.requireNonNull(builder.nodes, new IllegalArgumentException("nodes must be set"));
        this.nodes = builder.nodes;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Config copy) {
        Builder builder = new Builder();
        builder.projectVersion = copy.getProjectVersion();
        builder.projectName = copy.getProjectName();
        builder.pqletaConfigs = copy.getPqletaConfigs();
        builder.nodes = copy.getNodes();
        return builder;
    }

    public String getProjectVersion() {
        return projectVersion;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<PQLetaConfig> getPqletaConfigs() {
        return pqletaConfigs;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config config = (Config) o;
        return Objects.equals(projectVersion, config.projectVersion) && Objects.equals(projectName, config.projectName) && Objects.equals(pqletaConfigs, config.pqletaConfigs) && Objects.equals(nodes, config.nodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectVersion, projectName, pqletaConfigs, nodes);
    }

    @Override
    public String toString() {
        return "Config{" +
                "projectVersion='" + projectVersion + '\'' +
                ", projectName='" + projectName + '\'' +
                ", pqletaConfigs=" + pqletaConfigs +
                ", nodes=" + nodes +
                '}';
    }

    /**
     * {@code Config} builder static inner class.
     */
    public static final class Builder {
        private String projectVersion;
        private String projectName;
        private List<PQLetaConfig> pqletaConfigs;
        private List<Node> nodes;

        private Builder() {
        }

        /**
         * Sets the {@code projectVersion} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param projectVersion the {@code projectVersion} to set
         * @return a reference to this Builder
         */
        public Builder setProjectVersion(String projectVersion) {
            this.projectVersion = projectVersion;
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
         * Sets the {@code pqletaConfigs} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param pqletaConfigs the {@code pqletaConfigs} to set
         * @return a reference to this Builder
         */
        public Builder setPqletaConfigs(List<PQLetaConfig> pqletaConfigs) {
            this.pqletaConfigs = pqletaConfigs;
            return this;
        }

        /**
         * Sets the {@code nodes} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param nodes the {@code nodes} to set
         * @return a reference to this Builder
         */
        public Builder setNodes(List<Node> nodes) {
            this.nodes = nodes;
            return this;
        }

        /**
         * Returns a {@code Config} built from the parameters previously set.
         *
         * @return a {@code Config} built with parameters of this {@code Config.Builder}
         */
        public Config build() {
            return new Config(this);
        }
    }
}

