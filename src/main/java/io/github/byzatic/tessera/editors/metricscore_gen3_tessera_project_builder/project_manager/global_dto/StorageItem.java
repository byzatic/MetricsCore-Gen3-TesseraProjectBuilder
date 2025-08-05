package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class StorageItem {

    @SerializedName("id_name")
    private String idName;

    @SerializedName("description")
    private String description;

    @SerializedName("options")
    private List<StorageOptionItem> options;

    public StorageItem() {
    }

    private StorageItem(Builder builder) {
        options = builder.options;
        description = builder.description;
        idName = builder.idName;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(StorageItem copy) {
        Builder builder = new Builder();
        builder.options = copy.getOptions();
        builder.description = copy.getDescription();
        builder.idName = copy.getIdName();
        return builder;
    }

    public String getIdName() {
        return idName;
    }

    public String getDescription() {
        return description;
    }

    public List<StorageOptionItem> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorageItem that = (StorageItem) o;
        return Objects.equals(options, that.options) && Objects.equals(description, that.description) && Objects.equals(idName, that.idName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(options, description, idName);
    }

    @Override
    public String toString() {
        return "StorageItem{" +
                "options=" + options +
                ", description='" + description + '\'' +
                ", idName='" + idName + '\'' +
                '}';
    }

    /**
     * {@code StorageItem} builder static inner class.
     */
    public static final class Builder {
        private List<StorageOptionItem> options;
        private String description;
        private String idName;

        private Builder() {
        }

        /**
         * Sets the {@code options} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param options the {@code options} to set
         * @return a reference to this Builder
         */
        public Builder setOptions(List<StorageOptionItem> options) {
            this.options = options;
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
         * Returns a {@code StorageItem} built from the parameters previously set.
         *
         * @return a {@code StorageItem} built with parameters of this {@code StorageItem.Builder}
         */
        public StorageItem build() {
            return new StorageItem(this);
        }
    }
}