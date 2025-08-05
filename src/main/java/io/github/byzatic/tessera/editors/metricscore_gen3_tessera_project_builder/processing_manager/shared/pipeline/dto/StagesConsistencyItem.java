package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.processing_manager.shared.pipeline.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class StagesConsistencyItem {

    @SerializedName("position")
    private Integer position;

    @SerializedName("stage_id")
    private String stageId;

    public StagesConsistencyItem() {
    }

    private StagesConsistencyItem(Builder builder) {
        position = builder.position;
        stageId = builder.stageId;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(StagesConsistencyItem copy) {
        Builder builder = new Builder();
        builder.position = copy.getPosition();
        builder.stageId = copy.getStageId();
        return builder;
    }

    public Integer getPosition() {
        return position;
    }

    public String getStageId() {
        return stageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StagesConsistencyItem that = (StagesConsistencyItem) o;
        return Objects.equals(position, that.position) && Objects.equals(stageId, that.stageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, stageId);
    }

    @Override
    public String toString() {
        return "StagesConsistencyItem{" +
                "position=" + position +
                ", stageId='" + stageId + '\'' +
                '}';
    }

    /**
     * {@code StagesConsistencyItem} builder static inner class.
     */
    public static final class Builder {
        private Integer position;
        private String stageId;

        private Builder() {
        }

        /**
         * Sets the {@code position} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param position the {@code position} to set
         * @return a reference to this Builder
         */
        public Builder setPosition(Integer position) {
            this.position = position;
            return this;
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
         * Returns a {@code StagesConsistencyItem} built from the parameters previously set.
         *
         * @return a {@code StagesConsistencyItem} built with parameters of this {@code StagesConsistencyItem.Builder}
         */
        public StagesConsistencyItem build() {
            return new StagesConsistencyItem(this);
        }
    }
}