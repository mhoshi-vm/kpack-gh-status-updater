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
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * V1alpha2BuildSpecSourceGit
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2022-11-27T15:06:33.623Z[Etc/UTC]")
public class V1alpha2BuildSpecSourceGit {
    public static final String SERIALIZED_NAME_REVISION = "revision";
    public static final String SERIALIZED_NAME_URL = "url";
    @SerializedName(SERIALIZED_NAME_REVISION)
    private String revision;
    @SerializedName(SERIALIZED_NAME_URL)
    private String url;


    public V1alpha2BuildSpecSourceGit revision(String revision) {

        this.revision = revision;
        return this;
    }

    /**
     * Get revision
     *
     * @return revision
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public String getRevision() {
        return revision;
    }


    public void setRevision(String revision) {
        this.revision = revision;
    }


    public V1alpha2BuildSpecSourceGit url(String url) {

        this.url = url;
        return this;
    }

    /**
     * Get url
     *
     * @return url
     **/
    @javax.annotation.Nullable
    @ApiModelProperty(value = "")

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        V1alpha2BuildSpecSourceGit v1alpha2BuildSpecSourceGit = (V1alpha2BuildSpecSourceGit) o;
        return Objects.equals(this.revision, v1alpha2BuildSpecSourceGit.revision) &&
                Objects.equals(this.url, v1alpha2BuildSpecSourceGit.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(revision, url);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class V1alpha2BuildSpecSourceGit {\n");
        sb.append("    revision: ").append(toIndentedString(revision)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

