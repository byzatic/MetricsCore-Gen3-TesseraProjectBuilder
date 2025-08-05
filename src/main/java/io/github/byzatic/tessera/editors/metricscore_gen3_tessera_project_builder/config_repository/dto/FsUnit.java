package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.byzatic.commons.ObjectsUtils;

import java.util.Objects;

public class FsUnit {
    @JsonProperty("mountpoint")
    private String mountpoint = null;

    public FsUnit() {
    }

    private FsUnit(Builder builder) {
        ObjectsUtils.requireNonNull(builder.mountpoint, new IllegalArgumentException("mountpoint must be set"));
        this.mountpoint = builder.mountpoint;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(FsUnit copy) {
        Builder builder = new Builder();
        builder.mountpoint = copy.getMountpoint();
        return builder;
    }

    public String getMountpoint() {
        return mountpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FsUnit fsUnit = (FsUnit) o;
        return Objects.equals(mountpoint, fsUnit.mountpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mountpoint);
    }

    @Override
    public String toString() {
        return "FsUnit{" +
                "mountpoint='" + mountpoint + '\'' +
                '}';
    }


    /**
     * {@code FsUnit} builder static inner class.
     */
    public static final class Builder {
        private String mountpoint;

        private Builder() {
        }

        /**
         * Sets the {@code mountpoint} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param mountpoint the {@code mountpoint} to set
         * @return a reference to this Builder
         */
        public Builder setMountpoint(String mountpoint) {
            this.mountpoint = mountpoint;
            return this;
        }

        /**
         * Returns a {@code FsUnit} built from the parameters previously set.
         *
         * @return a {@code FsUnit} built with parameters of this {@code FsUnit.Builder}
         */
        public FsUnit build() {
            return new FsUnit(this);
        }
    }
}
