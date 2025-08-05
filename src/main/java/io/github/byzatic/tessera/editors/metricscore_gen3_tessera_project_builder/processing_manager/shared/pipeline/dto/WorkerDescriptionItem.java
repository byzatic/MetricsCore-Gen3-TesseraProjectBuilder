package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class WorkerDescriptionItem {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("configuration_files")
    private List<WorkerOptionItem> options;

    public WorkerDescriptionItem() {
    }

    private WorkerDescriptionItem(Builder builder) {
        name = builder.name;
        description = builder.description;
        options = builder.options;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(WorkerDescriptionItem copy) {
        Builder builder = new Builder();
        builder.name = copy.getName();
        builder.description = copy.getDescription();
        builder.options = copy.getOptions();
        return builder;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<WorkerOptionItem> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkerDescriptionItem that = (WorkerDescriptionItem) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, options);
    }

    @Override
    public String toString() {
        return "WorkersDescriptionItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", options=" + options +
                '}';
    }

    /**
     * {@code WorkersDescriptionItem} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private String description;
        private List<WorkerOptionItem> options;

        private Builder() {
        }

        /**
         * Sets the {@code name} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param name the {@code name} to set
         * @return a reference to this Builder
         */
        public Builder setName(String name) {
            this.name = name;
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
        public Builder setOptions(List<WorkerOptionItem> options) {
            this.options = options;
            return this;
        }

        /**
         * Returns a {@code WorkersDescriptionItem} built from the parameters previously set.
         *
         * @return a {@code WorkersDescriptionItem} built with parameters of this {@code WorkersDescriptionItem.Builder}
         */
        public WorkerDescriptionItem build() {
            return new WorkerDescriptionItem(this);
        }
    }
}