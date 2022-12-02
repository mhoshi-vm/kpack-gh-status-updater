package jp.co.vmware.tanzu.kpackghstatusupdater.controller;

import jp.co.vmware.tanzu.kpackghstatusupdater.repository.CommitStatusRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommitStatusController {

    CommitStatusRepository commitStatusRepository;

    public CommitStatusController(CommitStatusRepository commitStatusRepository) {
        this.commitStatusRepository = commitStatusRepository;
    }

    @GetMapping("/build/description")
    public String getDescription(@RequestParam Integer id) {
        return commitStatusRepository.findById(id).toString();
    }
}
