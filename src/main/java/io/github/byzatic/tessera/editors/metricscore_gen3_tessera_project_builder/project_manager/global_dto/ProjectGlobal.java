package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.global_dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ProjectGlobal {

    @SerializedName("storages")
    private List<StorageItem> storages;

    @SerializedName("services")
    private List<ServiceItem> services;

    public ProjectGlobal() {
    }

    private ProjectGlobal(Builder builder) {
        storages = builder.storages;
        services = builder.services;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProjectGlobal copy) {
        Builder builder = new Builder();
        builder.storages = copy.getStorages();
        builder.services = copy.getServices();
        return builder;
    }

    public List<StorageItem> getStorages() {
        return storages;
    }

    public List<ServiceItem> getServices() {
        return services;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectGlobal that = (ProjectGlobal) o;
        return Objects.equals(storages, that.storages) && Objects.equals(services, that.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storages, services);
    }

    @Override
    public String toString() {
        return "ProjectGlobal{" +
                "storages=" + storages +
                ", services=" + services +
                '}';
    }

    /**
     * {@code ProjectGlobal} builder static inner class.
     */
    public static final class Builder {
        private List<StorageItem> storages;
        private List<ServiceItem> services;

        private Builder() {
        }

        /**
         * Sets the {@code storages} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param storages the {@code storages} to set
         * @return a reference to this Builder
         */
        public Builder setStorages(List<StorageItem> storages) {
            this.storages = storages;
            return this;
        }

        /**
         * Sets the {@code services} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param services the {@code services} to set
         * @return a reference to this Builder
         */
        public Builder setServices(List<ServiceItem> services) {
            this.services = services;
            return this;
        }

        /**
         * Returns a {@code ProjectGlobal} built from the parameters previously set.
         *
         * @return a {@code ProjectGlobal} built with parameters of this {@code ProjectGlobal.Builder}
         */
        public ProjectGlobal build() {
            return new ProjectGlobal(this);
        }
    }
}