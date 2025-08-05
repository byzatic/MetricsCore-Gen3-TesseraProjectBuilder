package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ServerDescription {

    @SerializedName("ssl_verify")
    private String sslVerify;

    @SerializedName("url")
    private String url;

    public ServerDescription() {
    }

    private ServerDescription(Builder builder) {
        sslVerify = builder.sslVerify;
        url = builder.url;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ServerDescription copy) {
        Builder builder = new Builder();
        builder.sslVerify = copy.getSslVerify();
        builder.url = copy.getUrl();
        return builder;
    }

    public String getSslVerify() {
        return sslVerify;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerDescription that = (ServerDescription) o;
        return Objects.equals(sslVerify, that.sslVerify) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sslVerify, url);
    }

    @Override
    public String toString() {
        return "ServerDescription{" +
                "sslVerify='" + sslVerify + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    /**
     * {@code ServerDescription} builder static inner class.
     */
    public static final class Builder {
        private String sslVerify;
        private String url;

        private Builder() {
        }

        /**
         * Sets the {@code sslVerify} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param sslVerify the {@code sslVerify} to set
         * @return a reference to this Builder
         */
        public Builder setSslVerify(String sslVerify) {
            this.sslVerify = sslVerify;
            return this;
        }

        /**
         * Sets the {@code url} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param url the {@code url} to set
         * @return a reference to this Builder
         */
        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * Returns a {@code ServerDescription} built from the parameters previously set.
         *
         * @return a {@code ServerDescription} built with parameters of this {@code ServerDescription.Builder}
         */
        public ServerDescription build() {
            return new ServerDescription(this);
        }
    }
}