package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.project_manager.project_dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

public class ProjectNode {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("downstream")
    private List<ProjectNode> downstream;

    public ProjectNode() {
    }

    private ProjectNode(Builder builder) {
        name = builder.name;
        downstream = builder.downstream;
        description = builder.description;
        id = builder.id;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ProjectNode copy) {
        Builder builder = new Builder();
        builder.name = copy.getName();
        builder.downstream = copy.getDownstream();
        builder.description = copy.getDescription();
        builder.id = copy.getId();
        return builder;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<ProjectNode> getDownstream() {
        return downstream;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectNode projectNode = (ProjectNode) o;
        return Objects.equals(name, projectNode.name) && Objects.equals(downstream, projectNode.downstream) && Objects.equals(description, projectNode.description) && Objects.equals(id, projectNode.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, downstream, description, id);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", downstream=" + downstream +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    /**
     * {@code Node} builder static inner class.
     */
    public static final class Builder {
        private String name;
        private List<ProjectNode> downstream;
        private String description;
        private String id;

        private Builder() {
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
         * Sets the {@code downstream} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param downstream the {@code downstream} to set
         * @return a reference to this Builder
         */
        public Builder setDownstream(List<ProjectNode> downstream) {
            this.downstream = downstream;
            return this;
        }

        /**
         * Sets the {@code description} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param description the {@code description} to set
         * @return a reference to this Builder
         */
        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the {@code id} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param id the {@code id} to set
         * @return a reference to this Builder
         */
        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        /**
         * Returns a {@code Node} built from the parameters previously set.
         *
         * @return a {@code Node} built with parameters of this {@code Node.Builder}
         */
        public ProjectNode build() {
            return new ProjectNode(this);
        }
    }
}