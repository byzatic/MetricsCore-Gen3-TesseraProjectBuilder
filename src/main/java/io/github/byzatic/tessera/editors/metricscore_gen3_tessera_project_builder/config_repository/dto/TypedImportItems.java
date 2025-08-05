package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class TypedImportItems {
    @JsonProperty("type_name")
    private String typeName = null;
    @JsonProperty("pqleta_id")
    private String pqletaId = null;
    @JsonProperty("labels_for_filtering")
    private List<FilterLabel> labelsForFiltering = null;

    public TypedImportItems() {
    }

    private TypedImportItems(Builder builder) {
        typeName = builder.typeName;
        pqletaId = builder.pqletaId;
        labelsForFiltering = builder.labelsForFiltering;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(TypedImportItems copy) {
        Builder builder = new Builder();
        builder.typeName = copy.getTypeName();
        builder.pqletaId = copy.getPqletaId();
        builder.labelsForFiltering = copy.getLabelsForFiltering();
        return builder;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getPqletaId() {
        return pqletaId;
    }

    public List<FilterLabel> getLabelsForFiltering() {
        return labelsForFiltering;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypedImportItems that = (TypedImportItems) o;
        return Objects.equals(typeName, that.typeName) && Objects.equals(pqletaId, that.pqletaId) && Objects.equals(labelsForFiltering, that.labelsForFiltering);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName, pqletaId, labelsForFiltering);
    }

    @Override
    public String toString() {
        return "TypedImportItems{" +
                "typeName='" + typeName + '\'' +
                ", pqletaId='" + pqletaId + '\'' +
                ", labelsForFiltering=" + labelsForFiltering +
                '}';
    }

    /**
     * {@code TypedImportItems} builder static inner class.
     */
    public static final class Builder {
        private String typeName;
        private String pqletaId;
        private List<FilterLabel> labelsForFiltering;

        private Builder() {
        }

        /**
         * Sets the {@code typeName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param typeName the {@code typeName} to set
         * @return a reference to this Builder
         */
        public Builder setTypeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        /**
         * Sets the {@code pqletaId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param pqletaId the {@code pqletaId} to set
         * @return a reference to this Builder
         */
        public Builder setPqletaId(String pqletaId) {
            this.pqletaId = pqletaId;
            return this;
        }

        /**
         * Sets the {@code labelsForFiltering} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param labelsForFiltering the {@code labelsForFiltering} to set
         * @return a reference to this Builder
         */
        public Builder setLabelsForFiltering(List<FilterLabel> labelsForFiltering) {
            this.labelsForFiltering = labelsForFiltering;
            return this;
        }

        /**
         * Returns a {@code TypedImportItems} built from the parameters previously set.
         *
         * @return a {@code TypedImportItems} built with parameters of this {@code TypedImportItems.Builder}
         */
        public TypedImportItems build() {
            return new TypedImportItems(this);
        }
    }
}
