package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class LabelsItem {

    @SerializedName("sign")
    private String sign;

    @SerializedName("value")
    private String value;

    @SerializedName("key")
    private String key;

    private LabelsItem(Builder builder) {
        sign = builder.sign;
        value = builder.value;
        key = builder.key;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(LabelsItem copy) {
        Builder builder = new Builder();
        builder.sign = copy.getSign();
        builder.value = copy.getValue();
        builder.key = copy.getKey();
        return builder;
    }

    public String getSign() {
        return sign;
    }

    public String getValue() {
        return value;
    }

    public String getKey() {
        return key;
    }

    public LabelsItem() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelsItem that = (LabelsItem) o;
        return Objects.equals(sign, that.sign) && Objects.equals(value, that.value) && Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sign, value, key);
    }

    @Override
    public String toString() {
        return "LabelsItem{" +
                "sign='" + sign + '\'' +
                ", value='" + value + '\'' +
                ", key='" + key + '\'' +
                '}';
    }


    /**
     * {@code LabelsItem} builder static inner class.
     */
    public static final class Builder {
        private String sign;
        private String value;
        private String key;

        private Builder() {
        }

        /**
         * Sets the {@code sign} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param sign the {@code sign} to set
         * @return a reference to this Builder
         */
        public Builder setSign(String sign) {
            this.sign = sign;
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
         * Returns a {@code LabelsItem} built from the parameters previously set.
         *
         * @return a {@code LabelsItem} built with parameters of this {@code LabelsItem.Builder}
         */
        public LabelsItem build() {
            return new LabelsItem(this);
        }
    }
}