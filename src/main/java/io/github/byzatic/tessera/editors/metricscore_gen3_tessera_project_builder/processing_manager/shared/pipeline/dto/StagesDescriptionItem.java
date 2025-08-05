package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class StagesDescriptionItem {

    @SerializedName("stage_id")
    private String stageId;

    @SerializedName("workers_description")
    private List<WorkerDescriptionItem> workersDescription;

    public StagesDescriptionItem() {
    }

    private StagesDescriptionItem(Builder builder) {
        stageId = builder.stageId;
        workersDescription = builder.workersDescription;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(StagesDescriptionItem copy) {
        Builder builder = new Builder();
        builder.stageId = copy.getStageId();
        builder.workersDescription = copy.getWorkersDescription();
        return builder;
    }

    public String getStageId() {
        return stageId;
    }

    public List<WorkerDescriptionItem> getWorkersDescription() {
        return workersDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StagesDescriptionItem that = (StagesDescriptionItem) o;
        return Objects.equals(stageId, that.stageId) && Objects.equals(workersDescription, that.workersDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stageId, workersDescription);
    }

    @Override
    public String toString() {
        return "StagesDescriptionItem{" +
                "stageId='" + stageId + '\'' +
                ", workersDescription=" + workersDescription +
                '}';
    }

    /**
     * {@code StagesDescriptionItem} builder static inner class.
     */
    public static final class Builder {
        private String stageId;
        private List<WorkerDescriptionItem> workersDescription;

        private Builder() {
        }

        /**
         * Sets the {@code stageId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param stageId the {@code stageId} to set
         * @return a reference to this Builder
         */
        public Builder setStageId(String stageId) {
            this.stageId = stageId;
            return this;
        }

        /**
         * Sets the {@code workersDescription} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param workersDescription the {@code workersDescription} to set
         * @return a reference to this Builder
         */
        public Builder setWorkersDescription(List<WorkerDescriptionItem> workersDescription) {
            this.workersDescription = workersDescription;
            return this;
        }

        /**
         * Returns a {@code StagesDescriptionItem} built from the parameters previously set.
         *
         * @return a {@code StagesDescriptionItem} built with parameters of this {@code StagesDescriptionItem.Builder}
         */
        public StagesDescriptionItem build() {
            return new StagesDescriptionItem(this);
        }
    }
}