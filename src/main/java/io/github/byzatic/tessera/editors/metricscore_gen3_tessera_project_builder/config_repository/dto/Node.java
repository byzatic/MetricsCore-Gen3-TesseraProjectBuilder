package io.github.byzatic.tessera.editors.metricscore_gen3_tessera_project_builder.config_repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.byzatic.commons.ObjectsUtils;

import java.util.List;
import java.util.Objects;

public class Node {
    @JsonProperty("node_type")
    private String nodeType = null;
    @JsonProperty("node_id")
    private String nodeId = null;
    @JsonProperty("node_name")
    private String nodeName = null;
    @JsonProperty("data_type")
    private String dataType = null;
    @JsonProperty("data_name")
    private String dataName = null;
    @JsonProperty("data_origin")
    private List<String> dataOrigin = null;
    @JsonProperty("disable_mc_import_alarm")
    private List<String> disableMcImportAlarm = null;
    @JsonProperty("grafana_url")
    private String grafanaURL = null;
    @JsonProperty("downstream")
    private List<String> downstream = null;
    @JsonIgnore
    private Boolean isRoot = null;
    @JsonIgnore
    private String nodeFolderName = null;

    public Node() {
    }

    private Node(Builder builder) {
        ObjectsUtils.requireNonNull(builder.nodeType, new IllegalArgumentException("nodeType must be set"));
        this.nodeType = builder.nodeType;

        ObjectsUtils.requireNonNull(builder.nodeId, new IllegalArgumentException("nodeId must be set"));
        this.nodeId = builder.nodeId;

        ObjectsUtils.requireNonNull(builder.nodeName, new IllegalArgumentException("nodeName must be set"));
        this.nodeName = builder.nodeName;

        ObjectsUtils.requireNonNull(builder.dataType, new IllegalArgumentException("dataType must be set"));
        this.dataType = builder.dataType;

        ObjectsUtils.requireNonNull(builder.dataName, new IllegalArgumentException("dataName must be set"));
        this.dataName = builder.dataName;

        ObjectsUtils.requireNonNull(builder.dataOrigin, new IllegalArgumentException("dataOrigin must be set"));
        this.dataOrigin = builder.dataOrigin;

        ObjectsUtils.requireNonNull(builder.disableMcImportAlarm, new IllegalArgumentException("disableMcImportAlarm must be set"));
        this.disableMcImportAlarm = builder.disableMcImportAlarm;

        ObjectsUtils.requireNonNull(builder.grafanaURL, new IllegalArgumentException("grafanaURL must be set"));
        this.grafanaURL = builder.grafanaURL;

        ObjectsUtils.requireNonNull(builder.downstream, new IllegalArgumentException("downstream must be set"));
        this.downstream = builder.downstream;

        ObjectsUtils.requireNonNull(builder.isRoot, new IllegalArgumentException("isRoot must be set"));
        this.isRoot = builder.isRoot;

        if (builder.nodeId.equals("#NAMED")) {
            this.nodeFolderName = builder.nodeName;
        } else {
            this.nodeFolderName = builder.nodeId + "-" + builder.nodeName;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Node copy) {
        Builder builder = new Builder();
        builder.nodeType = copy.getNodeType();
        builder.nodeId = copy.getNodeId();
        builder.nodeName = copy.getNodeName();
        builder.dataType = copy.getDataType();
        builder.dataName = copy.getDataName();
        builder.dataOrigin = copy.getDataOrigin();
        builder.disableMcImportAlarm = copy.getDisableMcImportAlarm();
        builder.grafanaURL = copy.getGrafanaURL();
        builder.downstream = copy.getDownstream();
        builder.isRoot = copy.isRoot();
        return builder;
    }

    public String getNodeType() {
        return nodeType;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public List<String> getDataOrigin() {
        return dataOrigin;
    }

    public List<String> getDisableMcImportAlarm() {
        return disableMcImportAlarm;
    }

    public String getGrafanaURL() {
        return grafanaURL;
    }

    public List<String> getDownstream() {
        return downstream;
    }

    public Boolean isRoot() {
        return isRoot;
    }

    public String getNodeFolderName() {
        return nodeFolderName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(nodeType, node.nodeType) && Objects.equals(nodeId, node.nodeId) && Objects.equals(nodeName, node.nodeName) && Objects.equals(dataType, node.dataType) && Objects.equals(dataName, node.dataName) && Objects.equals(dataOrigin, node.dataOrigin) && Objects.equals(disableMcImportAlarm, node.disableMcImportAlarm) && Objects.equals(grafanaURL, node.grafanaURL) && Objects.equals(downstream, node.downstream) && Objects.equals(isRoot, node.isRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeType, nodeId, nodeName, dataType, dataName, dataOrigin, disableMcImportAlarm, grafanaURL, downstream, isRoot);
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeType='" + nodeType + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", nodeName='" + nodeName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", dataName='" + dataName + '\'' +
                ", dataOrigin=" + dataOrigin +
                ", disableMcImportAlarm=" + disableMcImportAlarm +
                ", grafanaURL='" + grafanaURL + '\'' +
                ", downstream=" + downstream +
                ", isRoot=" + isRoot +
                '}';
    }

    /**
     * {@code Node} builder static inner class.
     */
    public static final class Builder {
        private String nodeType;
        private String nodeId;
        private String nodeName;
        private String dataType;
        private String dataName;
        private List<String> dataOrigin;
        private List<String> disableMcImportAlarm;
        private String grafanaURL;
        private List<String> downstream;
        private Boolean isRoot;

        private Builder() {
        }

        /**
         * Sets the {@code nodeType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param nodeType the {@code nodeType} to set
         * @return a reference to this Builder
         */
        public Builder setNodeType(String nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        /**
         * Sets the {@code nodeId} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param nodeId the {@code nodeId} to set
         * @return a reference to this Builder
         */
        public Builder setNodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        /**
         * Sets the {@code nodeName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param nodeName the {@code nodeName} to set
         * @return a reference to this Builder
         */
        public Builder setNodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        /**
         * Sets the {@code dataType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dataType the {@code dataType} to set
         * @return a reference to this Builder
         */
        public Builder setDataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        /**
         * Sets the {@code dataName} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dataName the {@code dataName} to set
         * @return a reference to this Builder
         */
        public Builder setDataName(String dataName) {
            this.dataName = dataName;
            return this;
        }

        /**
         * Sets the {@code dataOrigin} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dataOrigin the {@code dataOrigin} to set
         * @return a reference to this Builder
         */
        public Builder setDataOrigin(List<String> dataOrigin) {
            this.dataOrigin = dataOrigin;
            return this;
        }

        /**
         * Sets the {@code disableMcImportAlarm} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param disableMcImportAlarm the {@code disableMcImportAlarm} to set
         * @return a reference to this Builder
         */
        public Builder setDisableMcImportAlarm(List<String> disableMcImportAlarm) {
            this.disableMcImportAlarm = disableMcImportAlarm;
            return this;
        }

        /**
         * Sets the {@code grafanaURL} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param grafanaURL the {@code grafanaURL} to set
         * @return a reference to this Builder
         */
        public Builder setGrafanaURL(String grafanaURL) {
            this.grafanaURL = grafanaURL;
            return this;
        }

        /**
         * Sets the {@code downstream} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param downstream the {@code downstream} to set
         * @return a reference to this Builder
         */
        public Builder setDownstream(List<String> downstream) {
            this.downstream = downstream;
            return this;
        }

        /**
         * Sets the {@code isRoot} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param isRoot the {@code isRoot} to set
         * @return a reference to this Builder
         */
        public Builder setIsRoot(Boolean isRoot) {
            this.isRoot = isRoot;
            return this;
        }

        /**
         * Returns a {@code Node} built from the parameters previously set.
         *
         * @return a {@code Node} built with parameters of this {@code Node.Builder}
         */
        public Node build() {
            return new Node(this);
        }
    }
}
