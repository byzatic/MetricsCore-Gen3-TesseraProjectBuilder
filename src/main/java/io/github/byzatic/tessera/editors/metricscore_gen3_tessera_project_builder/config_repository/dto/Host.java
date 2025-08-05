package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class Host {
    @JsonProperty("host_name")
    private String hostName = null;
    @JsonProperty("fs_unit")
    private List<FsUnit> fsUnits = null;

    public Host() {
    }

    private Host(Builder builder) {
        hostName = builder.hostName;
        fsUnits = builder.fsUnits;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Host copy) {
        Builder builder = new Builder();
        builder.hostName = copy.getHostName();
        builder.fsUnits = copy.getFsUnits();
        return builder;
    }

    public String getHostName() {
        return hostName;
    }

    public List<FsUnit> getFsUnits() {
        return fsUnits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Host host = (Host) o;
        return Objects.equals(hostName, host.hostName) && Objects.equals(fsUnits, host.fsUnits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hostName, fsUnits);
    }

    @Override
    public String toString() {
        return "Host{" +
                "hostName='" + hostName + '\'' +
                ", fsUnits=" + fsUnits +
                '}';
    }

    /**
     * {@code Host} builder static inner class.
     */
    public static final class Builder {
        private String hostName;
        private List<FsUnit> fsUnits;

        private Builder() {
        }

        /**
         * Sets the {@code hostName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param hostName the {@code hostName} to set
         * @return a reference to this Builder
         */
        public Builder setHostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        /**
         * Sets the {@code fsUnits} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param fsUnits the {@code fsUnits} to set
         * @return a reference to this Builder
         */
        public Builder setFsUnits(List<FsUnit> fsUnits) {
            this.fsUnits = fsUnits;
            return this;
        }

        /**
         * Returns a {@code Host} built from the parameters previously set.
         *
         * @return a {@code Host} built with parameters of this {@code Host.Builder}
         */
        public Host build() {
            return new Host(this);
        }
    }
}
