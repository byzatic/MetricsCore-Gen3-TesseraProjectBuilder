package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.pqleta_config_manager.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class QueryScope {

    @SerializedName("server_description")
    private ServerDescription serverDescription;

    @SerializedName("query_description")
    private List<QueryDescriptionItem> queryDescription;

    public QueryScope() {
    }

    private QueryScope(Builder builder) {
        serverDescription = builder.serverDescription;
        queryDescription = builder.queryDescription;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(QueryScope copy) {
        Builder builder = new Builder();
        builder.serverDescription = copy.getServerDescription();
        builder.queryDescription = copy.getQueryDescription();
        return builder;
    }

    public ServerDescription getServerDescription() {
        return serverDescription;
    }

    public List<QueryDescriptionItem> getQueryDescription() {
        return queryDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryScope that = (QueryScope) o;
        return Objects.equals(serverDescription, that.serverDescription) && Objects.equals(queryDescription, that.queryDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverDescription, queryDescription);
    }

    @Override
    public String toString() {
        return "QueryScope{" +
                "serverDescription=" + serverDescription +
                ", queryDescription=" + queryDescription +
                '}';
    }


    /**
     * {@code QueryScope} builder static inner class.
     */
    public static final class Builder {
        private ServerDescription serverDescription;
        private List<QueryDescriptionItem> queryDescription;

        private Builder() {
        }

        /**
         * Sets the {@code serverDescription} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param serverDescription the {@code serverDescription} to set
         * @return a reference to this Builder
         */
        public Builder setServerDescription(ServerDescription serverDescription) {
            this.serverDescription = serverDescription;
            return this;
        }

        /**
         * Sets the {@code queryDescription} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param queryDescription the {@code queryDescription} to set
         * @return a reference to this Builder
         */
        public Builder setQueryDescription(List<QueryDescriptionItem> queryDescription) {
            this.queryDescription = queryDescription;
            return this;
        }

        /**
         * Returns a {@code QueryScope} built from the parameters previously set.
         *
         * @return a {@code QueryScope} built with parameters of this {@code QueryScope.Builder}
         */
        public QueryScope build() {
            return new QueryScope(this);
        }
    }
}