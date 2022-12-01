package jp.co.vmware.tanzu.kpackghstatusupdater.models;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CommitStatus {
    @Id
    private String id;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
