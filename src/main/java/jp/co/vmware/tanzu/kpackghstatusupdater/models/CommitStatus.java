package jp.co.vmware.tanzu.kpackghstatusupdater.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommitStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String kpackKey;

    private String sha;

    private String success;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKpackKey() {
        return kpackKey;
    }

    public void setKpackKey(String key) {
        this.kpackKey = key;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
