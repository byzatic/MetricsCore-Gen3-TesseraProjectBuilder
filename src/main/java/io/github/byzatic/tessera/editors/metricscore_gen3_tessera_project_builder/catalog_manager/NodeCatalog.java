package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.catalog_manager;

import java.nio.file.Path;
import java.util.Objects;

public class NodeCatalog {
    private String nodeName = null;
    private Path catalog = null;
    private Path configurationFilesCatalog = null;

    public NodeCatalog() {
    }

    private NodeCatalog(Builder builder) {
        nodeName = builder.nodeName;
        catalog = builder.catalog;
        configurationFilesCatalog = builder.configurationFilesCatalog;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(NodeCatalog copy) {
        Builder builder = new Builder();
        builder.nodeName = copy.getNodeName();
        builder.catalog = copy.getCatalog();
        builder.configurationFilesCatalog = copy.getConfigurationFilesCatalog();
        return builder;
    }

    public String getNodeName() {
        return nodeName;
    }

    public Path getCatalog() {
        return catalog;
    }

    public Path getConfigurationFilesCatalog() {
        return configurationFilesCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeCatalog that = (NodeCatalog) o;
        return Objects.equals(nodeName, that.nodeName) && Objects.equals(catalog, that.catalog) && Objects.equals(configurationFilesCatalog, that.configurationFilesCatalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeName, catalog, configurationFilesCatalog);
    }

    @Override
    public String toString() {
        return "NodeCatalog{" +
                "nodeName='" + nodeName + '\'' +
                ", catalog=" + catalog +
                ", configurationFilesCatalog=" + configurationFilesCatalog +
                '}';
    }

    /**
     * {@code NodeCatalog} builder static inner class.
     */
    public static final class Builder {
        private String nodeName;
        private Path catalog;
        private Path configurationFilesCatalog;

        private Builder() {
        }

        /**
         * Sets the {@code nodeName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param nodeName the {@code nodeName} to set
         * @return a reference to this Builder
         */
        public Builder setNodeName(String nodeName) {
            this.nodeName = nodeName;
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
         * Returns a {@code NodeCatalog} built from the parameters previously set.
         *
         * @return a {@code NodeCatalog} built with parameters of this {@code NodeCatalog.Builder}
         */
        public NodeCatalog build() {
            return new NodeCatalog(this);
        }
    }
}
