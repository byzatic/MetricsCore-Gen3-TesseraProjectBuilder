package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class StorageOptionItem {

    @SerializedName("key")
    private String key;

    @SerializedName("value")
    private String value;

    public StorageOptionItem() {
    }

    private StorageOptionItem(Builder builder) {
        value = builder.value;
        key = builder.key;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(StorageOptionItem copy) {
        Builder builder = new Builder();
        builder.value = copy.getValue();
        builder.key = copy.getKey();
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
        StorageOptionItem that = (StorageOptionItem) o;
        return Objects.equals(value, that.value) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, key);
    }

    @Override
    public String toString() {
        return "StorageOptionItem{" +
                "value='" + value + '\'' +
                ", key='" + key + '\'' +
                '}';
    }

    /**
     * {@code StorageOptionItem} builder static inner class.
     */
    public static final class Builder {
        private String value;
        private String key;

        private Builder() {
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
         * Returns a {@code StorageOptionItem} built from the parameters previously set.
         *
         * @return a {@code StorageOptionItem} built with parameters of this {@code StorageOptionItem.Builder}
         */
        public StorageOptionItem build() {
            return new StorageOptionItem(this);
        }
    }
}