package jp.co.vmware.tanzu.kpackghstatusupdater.repository;

import jp.co.vmware.tanzu.kpackghstatusupdater.models.CommitStatus;
import org.springframework.data.repository.CrudRepository;

public interface CommitStatusRepository extends CrudRepository<CommitStatus, String> {
}
