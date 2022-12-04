package jp.co.vmware.tanzu.kpackghstatusupdater.repository;

import jp.co.vmware.tanzu.kpackghstatusupdater.models.CommitStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommitStatusRepository extends CrudRepository<CommitStatus, Integer> {

    List<CommitStatus> findByKpackKeyIsAndShaIs(String key, String sha);

    List<CommitStatus> findCommitStatusesBySha(String sha);

    void deleteCommitStatusesByKpackKey(String key);
}
