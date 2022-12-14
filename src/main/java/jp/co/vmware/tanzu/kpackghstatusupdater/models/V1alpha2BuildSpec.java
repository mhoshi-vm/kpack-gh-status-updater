/*
 * Kubernetes
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v1.21.1
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package jp.co.vmware.tanzu.kpackghstatusupdater.models;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BuildSpec defines the desired state of Build
 */
@ApiModel(description = "BuildSpec defines the desired state of Build")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-11-27T15:06:33.623Z[Etc/UTC]")
public class V1alpha2BuildSpec {
    public static final String SERIALIZED_NAME_ACTIVE_DEADLINE_SECONDS = "activeDeadlineSeconds";
    public static final String SERIALIZED_NAME_AFFINITY = "affinity";
    public static final String SERIALIZED_NAME_BUILDER = "builder";
    public static final String SERIALIZED_NAME_CACHE = "cache";
    public static final String SERIALIZED_NAME_DEFAULT_PROCESS = "defaultProcess";
    public static final String SERIALIZED_NAME_ENV = "env";
    public static final String SERIALIZED_NAME_NODE_SELECTOR = "nodeSelector";
    public static final String SERIALIZED_NAME_PROJECT_DESCRIPTOR_PATH = "projectDescriptorPath";
    public static final String SERIALIZED_NAME_RESOURCES = "resources";
    public static final String SERIALIZED_NAME_SERVICE_ACCOUNT_NAME = "serviceAccountName";
    public static final String SERIALIZED_NAME_SOURCE = "source";
    public static final String SERIALIZED_NAME_TAGS = "tags";
    public static final String SERIALIZED_NAME_TOLERATIONS = "tolerations";
    @SerializedName(SERIALIZED_NAME_ACTIVE_DEADLINE_SECONDS)
    private Integer activeDeadlineSeconds;
    @SerializedName(SERIALIZED_NAME_AFFINITY)
    private Object affinity;
    @SerializedName(SERIALIZED_NAME_BUILDER)
    private Object builder;
    @SerializedName(SERIALIZED_NAME_CACHE)
    private Object cache;
    @SerializedName(SERIALIZED_NAME_DEFAULT_PROCESS)
    private Object defaultProcess;
    @SerializedName(SERIALIZED_NAME_ENV)
    private Object env;
    @SerializedName(SERIALIZED_NAME_NODE_SELECTOR)
    private Object nodeSelector;
    @SerializedName(SERIALIZED_NAME_PROJECT_DESCRIPTOR_PATH)
    private Object projectDescriptorPath;
    @SerializedName(SERIALIZED_NAME_RESOURCES)
    private Object resources;
    @SerializedName(SERIALIZED_NAME_SERVICE_ACCOUNT_NAME)
    private String serviceAccountName;
    @SerializedName(SERIALIZED_NAME_SOURCE)
    private V1alpha2BuildSpecSource source;
    @SerializedName(SERIALIZED_NAME_TAGS)
    private List<String> tags = null;
    @SerializedName(SERIALIZED_NAME_TOLERATIONS)
    private Object tolerations;


    public V1alpha2BuildSpec activeDeadlineSeconds(Integer activeDeadlineSeconds) {

        this.activeDeadlineSeconds = activeDeadlineSeconds;
        return this;
    }

    /**
     * Get activeDeadlineSeconds
     *
     * @return activeDeadlineSeconds
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Integer getActiveDeadlineSeconds() {
        return activeDeadlineSeconds;
    }


    public void setActiveDeadlineSeconds(Integer activeDeadlineSeconds) {
        this.activeDeadlineSeconds = activeDeadlineSeconds;
    }


    public V1alpha2BuildSpec affinity(Object affinity) {

        this.affinity = affinity;
        return this;
    }

    /**
     * Get affinity
     *
     * @return affinity
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getAffinity() {
        return affinity;
    }


    public void setAffinity(Object affinity) {
        this.affinity = affinity;
    }


    public V1alpha2BuildSpec builder(Object builder) {

        this.builder = builder;
        return this;
    }

    /**
     * Get builder
     *
     * @return builder
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getBuilder() {
        return builder;
    }


    public void setBuilder(Object builder) {
        this.builder = builder;
    }


    public V1alpha2BuildSpec cache(Object cache) {

        this.cache = cache;
        return this;
    }

    /**
     * Get cache
     *
     * @return cache
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getCache() {
        return cache;
    }


    public void setCache(Object cache) {
        this.cache = cache;
    }


    public V1alpha2BuildSpec defaultProcess(Object defaultProcess) {

        this.defaultProcess = defaultProcess;
        return this;
    }

    /**
     * Get defaultProcess
     *
     * @return defaultProcess
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getDefaultProcess() {
        return defaultProcess;
    }


    public void setDefaultProcess(Object defaultProcess) {
        this.defaultProcess = defaultProcess;
    }


    public V1alpha2BuildSpec env(Object env) {

        this.env = env;
        return this;
    }

    /**
     * Get env
     *
     * @return env
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getEnv() {
        return env;
    }


    public void setEnv(Object env) {
        this.env = env;
    }


    public V1alpha2BuildSpec nodeSelector(Object nodeSelector) {

        this.nodeSelector = nodeSelector;
        return this;
    }

    /**
     * Get nodeSelector
     *
     * @return nodeSelector
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getNodeSelector() {
        return nodeSelector;
    }


    public void setNodeSelector(Object nodeSelector) {
        this.nodeSelector = nodeSelector;
    }


    public V1alpha2BuildSpec projectDescriptorPath(Object projectDescriptorPath) {

        this.projectDescriptorPath = projectDescriptorPath;
        return this;
    }

    /**
     * Get projectDescriptorPath
     *
     * @return projectDescriptorPath
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getProjectDescriptorPath() {
        return projectDescriptorPath;
    }


    public void setProjectDescriptorPath(Object projectDescriptorPath) {
        this.projectDescriptorPath = projectDescriptorPath;
    }


    public V1alpha2BuildSpec resources(Object resources) {

        this.resources = resources;
        return this;
    }

    /**
     * Get resources
     *
     * @return resources
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getResources() {
        return resources;
    }


    public void setResources(Object resources) {
        this.resources = resources;
    }


    public V1alpha2BuildSpec serviceAccountName(String serviceAccountName) {

        this.serviceAccountName = serviceAccountName;
        return this;
    }

    /**
     * Get serviceAccountName
     *
     * @return serviceAccountName
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public String getServiceAccountName() {
        return serviceAccountName;
    }


    public void setServiceAccountName(String serviceAccountName) {
        this.serviceAccountName = serviceAccountName;
    }


    public V1alpha2BuildSpec source(V1alpha2BuildSpecSource source) {

        this.source = source;
        return this;
    }

    /**
     * Get source
     *
     * @return source
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public V1alpha2BuildSpecSource getSource() {
        return source;
    }


    public void setSource(V1alpha2BuildSpecSource source) {
        this.source = source;
    }


    public V1alpha2BuildSpec tags(List<String> tags) {

        this.tags = tags;
        return this;
    }

    public V1alpha2BuildSpec addTagsItem(String tagsItem) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }
        this.tags.add(tagsItem);
        return this;
    }

    /**
     * Get tags
     *
     * @return tags
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public List<String> getTags() {
        return tags;
    }


    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public V1alpha2BuildSpec tolerations(Object tolerations) {

        this.tolerations = tolerations;
        return this;
    }

    /**
     * Get tolerations
     *
     * @return tolerations
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public Object getTolerations() {
        return tolerations;
    }


    public void setTolerations(Object tolerations) {
        this.tolerations = tolerations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        V1alpha2BuildSpec v1alpha2BuildSpec = (V1alpha2BuildSpec) o;
        return Objects.equals(this.activeDeadlineSeconds, v1alpha2BuildSpec.activeDeadlineSeconds) &&
                Objects.equals(this.affinity, v1alpha2BuildSpec.affinity) &&
                Objects.equals(this.builder, v1alpha2BuildSpec.builder) &&
                Objects.equals(this.cache, v1alpha2BuildSpec.cache) &&
                Objects.equals(this.defaultProcess, v1alpha2BuildSpec.defaultProcess) &&
                Objects.equals(this.env, v1alpha2BuildSpec.env) &&
                Objects.equals(this.nodeSelector, v1alpha2BuildSpec.nodeSelector) &&
                Objects.equals(this.projectDescriptorPath, v1alpha2BuildSpec.projectDescriptorPath) &&
                Objects.equals(this.resources, v1alpha2BuildSpec.resources) &&
                Objects.equals(this.serviceAccountName, v1alpha2BuildSpec.serviceAccountName) &&
                Objects.equals(this.source, v1alpha2BuildSpec.source) &&
                Objects.equals(this.tags, v1alpha2BuildSpec.tags) &&
                Objects.equals(this.tolerations, v1alpha2BuildSpec.tolerations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(activeDeadlineSeconds, affinity, builder, cache, defaultProcess, env, nodeSelector, projectDescriptorPath, resources, serviceAccountName, source, tags, tolerations);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class V1alpha2BuildSpec {\n");
        sb.append("    activeDeadlineSeconds: ").append(toIndentedString(activeDeadlineSeconds)).append("\n");
        sb.append("    affinity: ").append(toIndentedString(affinity)).append("\n");
        sb.append("    builder: ").append(toIndentedString(builder)).append("\n");
        sb.append("    cache: ").append(toIndentedString(cache)).append("\n");
        sb.append("    defaultProcess: ").append(toIndentedString(defaultProcess)).append("\n");
        sb.append("    env: ").append(toIndentedString(env)).append("\n");
        sb.append("    nodeSelector: ").append(toIndentedString(nodeSelector)).append("\n");
        sb.append("    projectDescriptorPath: ").append(toIndentedString(projectDescriptorPath)).append("\n");
        sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
        sb.append("    serviceAccountName: ").append(toIndentedString(serviceAccountName)).append("\n");
        sb.append("    source: ").append(toIndentedString(source)).append("\n");
        sb.append("    tags: ").append(toIndentedString(tags)).append("\n");
        sb.append("    tolerations: ").append(toIndentedString(tolerations)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

