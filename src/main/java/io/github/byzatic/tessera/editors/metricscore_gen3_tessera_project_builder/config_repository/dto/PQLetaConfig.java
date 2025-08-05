package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class PQLetaConfig {
    @JsonProperty("source_url")
    private String sourceURL = null;
    @JsonProperty("hosts")
    private List<Host> hosts = null;
    @JsonProperty("import_by_type")
    private List<TypedImportItems> importByType = null;

    public PQLetaConfig() {
    }

    private PQLetaConfig(Builder builder) {
        sourceURL = builder.sourceURL;
        hosts = builder.hosts;
        importByType = builder.importByType;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PQLetaConfig copy) {
        Builder builder = new Builder();
        builder.sourceURL = copy.getSourceURL();
        builder.hosts = copy.getHosts();
        builder.importByType = copy.getImportByType();
        return builder;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    public List<TypedImportItems> getImportByType() {
        return importByType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PQLetaConfig that = (PQLetaConfig) o;
        return Objects.equals(sourceURL, that.sourceURL) && Objects.equals(hosts, that.hosts) && Objects.equals(importByType, that.importByType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceURL, hosts, importByType);
    }

    @Override
    public String toString() {
        return "PQLetaConfig{" +
                "sourceURL='" + sourceURL + '\'' +
                ", hosts=" + hosts +
                ", importByType=" + importByType +
                '}';
    }


    /**
     * {@code PQLetaConfig} builder static inner class.
     */
    public static final class Builder {
        private String sourceURL;
        private List<Host> hosts;
        private List<TypedImportItems> importByType;

        private Builder() {
        }

        /**
         * Sets the {@code sourceURL} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param sourceURL the {@code sourceURL} to set
         * @return a reference to this Builder
         */
        public Builder setSourceURL(String sourceURL) {
            this.sourceURL = sourceURL;
            return this;
        }

        /**
         * Sets the {@code hosts} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param hosts the {@code hosts} to set
         * @return a reference to this Builder
         */
        public Builder setHosts(List<Host> hosts) {
            this.hosts = hosts;
            return this;
        }

        /**
         * Sets the {@code importByType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param importByType the {@code importByType} to set
         * @return a reference to this Builder
         */
        public Builder setImportByType(List<TypedImportItems> importByType) {
            this.importByType = importByType;
            return this;
        }

        /**
         * Returns a {@code PQLetaConfig} built from the parameters previously set.
         *
         * @return a {@code PQLetaConfig} built with parameters of this {@code PQLetaConfig.Builder}
         */
        public PQLetaConfig build() {
            return new PQLetaConfig(this);
        }
    }
}
