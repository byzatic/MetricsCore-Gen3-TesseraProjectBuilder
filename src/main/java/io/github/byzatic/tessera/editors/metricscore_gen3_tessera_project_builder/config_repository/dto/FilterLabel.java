package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FilterLabel {
    @JsonProperty("key")
    private String key = null;
    @JsonProperty("value")
    private String value = null;

    public FilterLabel() {
    }

    private FilterLabel(Builder builder) {
        key = builder.key;
        value = builder.value;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(FilterLabel copy) {
        Builder builder = new Builder();
        builder.key = copy.getKey();
        builder.value = copy.getValue();
        return builder;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterLabel that = (FilterLabel) o;
        return Objects.equals(key, that.key) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "RootMetricLabel{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    /**
     * {@code RootMetricLabel} builder static inner class.
     */
    public static final class Builder {
        private String key;
        private String value;

        private Builder() {
        }

        /**
         * Sets the {@code key} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param key the {@code key} to set
         * @return a reference to this Builder
         */
        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        /**
         * Sets the {@code value} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param value the {@code value} to set
         * @return a reference to this Builder
         */
        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        /**
         * Returns a {@code RootMetricLabel} built from the parameters previously set.
         *
         * @return a {@code RootMetricLabel} built with parameters of this {@code RootMetricLabel.Builder}
         */
        public FilterLabel build() {
            return new FilterLabel(this);
        }
    }
}
