package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.global.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class StorageItem {

    @SerializedName("id_name")
    private String idName;
    @SerializedName("description")
    private String description;
    @SerializedName("options")
    private List<Object> options;

    public StorageItem() {
    }

    private StorageItem(Builder builder) {
        idName = builder.idName;
        description = builder.description;
        options = builder.options;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(StorageItem copy) {
        Builder builder = new Builder();
        builder.idName = copy.getIdName();
        builder.description = copy.getDescription();
        builder.options = copy.getOptions();
        return builder;
    }

    public String getIdName() {
        return idName;
    }

    public String getDescription() {
        return description;
    }

    public List<Object> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageItem that = (StorageItem) o;
        return Objects.equals(idName, that.idName) && Objects.equals(description, that.description) && Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idName, description, options);
    }

    @Override
    public String toString() {
        return "StoragesItem{" +
                "idName='" + idName + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                '}';
    }

    /**
     * {@code StoragesItem} builder static inner class.
     */
    public static final class Builder {
        private String idName;
        private String description;
        private List<Object> options;

        private Builder() {
        }

        /**
         * Sets the {@code idName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param idName the {@code idName} to set
         * @return a reference to this Builder
         */
        public Builder setIdName(String idName) {
            this.idName = idName;
            return this;
        }

        /**
         * Sets the {@code description} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param description the {@code description} to set
         * @return a reference to this Builder
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the {@code options} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param options the {@code options} to set
         * @return a reference to this Builder
         */
        public Builder setOptions(List<Object> options) {
            this.options = options;
            return this;
        }

        /**
         * Returns a {@code StoragesItem} built from the parameters previously set.
         *
         * @return a {@code StoragesItem} built with parameters of this {@code StoragesItem.Builder}
         */
        public StorageItem build() {
            return new StorageItem(this);
        }
    }
}