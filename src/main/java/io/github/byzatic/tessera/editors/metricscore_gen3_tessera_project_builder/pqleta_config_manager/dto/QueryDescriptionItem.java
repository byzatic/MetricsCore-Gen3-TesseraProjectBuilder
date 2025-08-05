package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class QueryDescriptionItem {

    @SerializedName("identifier")
    private String identifier;

    @SerializedName("lower_limit")
    private String lowerLimit;

    @SerializedName("time_range")
    private String timeRange;

    @SerializedName("upper_limit")
    private String upperLimit;

    @SerializedName("step")
    private String step;

    @SerializedName("type")
    private String type;

    @SerializedName("labels")
    private List<LabelsItem> labels;

    public QueryDescriptionItem() {
    }

    private QueryDescriptionItem(Builder builder) {
        identifier = builder.identifier;
        lowerLimit = builder.lowerLimit;
        timeRange = builder.timeRange;
        upperLimit = builder.upperLimit;
        step = builder.step;
        type = builder.type;
        labels = builder.labels;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(QueryDescriptionItem copy) {
        Builder builder = new Builder();
        builder.identifier = copy.getIdentifier();
        builder.lowerLimit = copy.getLowerLimit();
        builder.timeRange = copy.getTimeRange();
        builder.upperLimit = copy.getUpperLimit();
        builder.step = copy.getStep();
        builder.type = copy.getType();
        builder.labels = copy.getLabels();
        return builder;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getLowerLimit() {
        return lowerLimit;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public String getUpperLimit() {
        return upperLimit;
    }

    public String getStep() {
        return step;
    }

    public String getType() {
        return type;
    }

    public List<LabelsItem> getLabels() {
        return labels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryDescriptionItem that = (QueryDescriptionItem) o;
        return Objects.equals(identifier, that.identifier) && Objects.equals(lowerLimit, that.lowerLimit) && Objects.equals(timeRange, that.timeRange) && Objects.equals(upperLimit, that.upperLimit) && Objects.equals(step, that.step) && Objects.equals(type, that.type) && Objects.equals(labels, that.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, lowerLimit, timeRange, upperLimit, step, type, labels);
    }

    @Override
    public String toString() {
        return "QueryDescriptionItem{" +
                "identifier='" + identifier + '\'' +
                ", lowerLimit='" + lowerLimit + '\'' +
                ", timeRange='" + timeRange + '\'' +
                ", upperLimit='" + upperLimit + '\'' +
                ", step='" + step + '\'' +
                ", type='" + type + '\'' +
                ", labels=" + labels +
                '}';
    }


    /**
     * {@code QueryDescriptionItem} builder static inner class.
     */
    public static final class Builder {
        private String identifier;
        private String lowerLimit;
        private String timeRange;
        private String upperLimit;
        private String step;
        private String type;
        private List<LabelsItem> labels;

        private Builder() {
        }

        /**
         * Sets the {@code identifier} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param identifier the {@code identifier} to set
         * @return a reference to this Builder
         */
        public Builder setIdentifier(String identifier) {
            this.identifier = identifier;
            return this;
        }

        /**
         * Sets the {@code lowerLimit} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param lowerLimit the {@code lowerLimit} to set
         * @return a reference to this Builder
         */
        public Builder setLowerLimit(String lowerLimit) {
            this.lowerLimit = lowerLimit;
            return this;
        }

        /**
         * Sets the {@code timeRange} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param timeRange the {@code timeRange} to set
         * @return a reference to this Builder
         */
        public Builder setTimeRange(String timeRange) {
            this.timeRange = timeRange;
            return this;
        }

        /**
         * Sets the {@code upperLimit} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param upperLimit the {@code upperLimit} to set
         * @return a reference to this Builder
         */
        public Builder setUpperLimit(String upperLimit) {
            this.upperLimit = upperLimit;
            return this;
        }

        /**
         * Sets the {@code step} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param step the {@code step} to set
         * @return a reference to this Builder
         */
        public Builder setStep(String step) {
            this.step = step;
            return this;
        }

        /**
         * Sets the {@code type} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param type the {@code type} to set
         * @return a reference to this Builder
         */
        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        /**
         * Sets the {@code labels} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param labels the {@code labels} to set
         * @return a reference to this Builder
         */
        public Builder setLabels(List<LabelsItem> labels) {
            this.labels = labels;
            return this;
        }

        /**
         * Returns a {@code QueryDescriptionItem} built from the parameters previously set.
         *
         * @return a {@code QueryDescriptionItem} built with parameters of this {@code QueryDescriptionItem.Builder}
         */
        public QueryDescriptionItem build() {
            return new QueryDescriptionItem(this);
        }
    }
}