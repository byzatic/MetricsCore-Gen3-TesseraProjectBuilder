package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ServiceOptionItem {

    @SerializedName("data")
    private String data;

    @SerializedName("name")
    private String name;

    public ServiceOptionItem() {
    }

    private ServiceOptionItem(Builder builder) {
        data = builder.data;
        name = builder.name;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ServiceOptionItem copy) {
        Builder builder = new Builder();
        builder.data = copy.getData();
        builder.name = copy.getName();
        return builder;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOptionItem that = (ServiceOptionItem) o;
        return Objects.equals(data, that.data) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, name);
    }

    @Override
    public String toString() {
        return "ServiceOptionItem{" +
                "data='" + data + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    /**
     * {@code ServiceOptionItem} builder static inner class.
     */
    public static final class Builder {
        private String data;
        private String name;

        private Builder() {
        }

        /**
         * Sets the {@code data} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param data the {@code data} to set
         * @return a reference to this Builder
         */
        public Builder setData(String data) {
            this.data = data;
            return this;
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
         * Returns a {@code ServiceOptionItem} built from the parameters previously set.
         *
         * @return a {@code ServiceOptionItem} built with parameters of this {@code ServiceOptionItem.Builder}
         */
        public ServiceOptionItem build() {
            return new ServiceOptionItem(this);
        }
    }
}