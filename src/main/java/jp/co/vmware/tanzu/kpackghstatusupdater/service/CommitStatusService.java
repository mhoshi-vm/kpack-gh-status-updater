package jp.co.vmware.tanzu.kpackghstatusupdater.service;

import com.fasterxml.jackson.databind.JsonNode;
import jp.co.vmware.tanzu.kpackghstatusupdater.models.CommitStatus;
import jp.co.vmware.tanzu.kpackghstatusupdater.repository.CommitStatusRepository;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CommitStatusService {

    private static final Logger logger = LoggerFactory.getLogger(CommitStatusService.class);

    private final RestTemplate restTemplate;
    private final CommitStatusRepository commitStatusRepository;
    private final String githubToken;
    Map<String, String> statusMap = Map.of("True", "success",
            "False", "error", "Pending", "pending");

    public CommitStatusService(RestTemplateBuilder builder, CommitStatusRepository commitStatusRepository, @Value("${github.token}") String githubToken) {
        this.restTemplate = builder.build();
        this.githubToken = githubToken;
        this.commitStatusRepository = commitStatusRepository;
    }

    // https://docs.github.com/en/rest/commits/statuses
    public void updateStatus(String key, String url, String sha, String success, List<String> tags) {

        logger.info("Updating database");
        updateStatusToRepo(key, sha, success);

        String status = determineStatus(sha);
        logger.info("Desired status :" + status);

        String apiUrl = null;
        HttpHeaders headers = new HttpHeaders();
        String statusKey = "state";

        if (url.startsWith("https://github.com/")) {
            headers.set("Accept", "application/vnd.github+json");
            headers.set("Authorization", "Bearer " + githubToken);
            apiUrl = url.replace("https://github.com/", "https://api.github.com/repos/");
            statusKey = "state";
        } else if (url.contains("gitea")) {
            headers.set("Accept", "application/json");
            headers.set("Content-Type", "application/json");
            headers.set("Authorization", "token " + githubToken);
            apiUrl = url.replaceAll("^(https*://[^/]*)(.*)", "$1/api/v1/repos$2");
            statusKey = "status";
        }

        String targetUrl = apiUrl + "/statuses/" + sha;
        String desiredStatus = statusMap.get(status);
        HttpEntity<Void> getEntity = new HttpEntity<>(headers);

        JsonNode responseBody = restTemplate.exchange(targetUrl, HttpMethod.GET, getEntity, JsonNode.class).getBody();
        if (responseBody != null && responseBody.get(0) != null && responseBody.get(0).get(statusKey) != null) {
            String latestStatus = responseBody.get(0).get(statusKey).textValue();

            if (latestStatus.equals(desiredStatus)) {
                logger.info("No status change for " + key + " : " + sha + " , skipping");
                return;
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", desiredStatus);
        jsonObject.put("context", "Kpack");

        if (tags.size() > 0) {
            String descriptionUrl = tags.get(0).replaceAll("^([^/]*)(.*)", "https://$1");
            jsonObject.put("description", "image :" + tags.get(0));
            jsonObject.put("target_url", descriptionUrl);
        }
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(), headers);
        logger.info("Executing API :" + targetUrl);
        restTemplate.postForObject(targetUrl, httpEntity, String.class);
    }

    public void updateStatusToRepo(String key, String sha, String success) {
        CommitStatus commitStatus = new CommitStatus();
        List<CommitStatus> commitStatusList = commitStatusRepository.findByKpackKeyIsAndShaIs(key, sha);
        if (commitStatusList.size() == 1) {
            commitStatus.setId(commitStatusList.get(0).getId());
        }
        commitStatus.setKpackKey(key);
        commitStatus.setSha(sha);
        commitStatus.setSuccess(success);
        commitStatusRepository.save(commitStatus);
    }

    private String determineStatus(String sha) {

        List<CommitStatus> commitStatusList = commitStatusRepository.findCommitStatusesBySha(sha);

        List<String> suceessList = new ArrayList<>();
        for (CommitStatus commitStatus : commitStatusList) {
            if (commitStatus.getSuccess().equals("False")) {
                return commitStatus.getSuccess();
            }
            suceessList.add(commitStatus.getSuccess());
        }

        if (!suceessList.isEmpty() && suceessList.stream().allMatch("True"::equals)) {
            return suceessList.get(0);
        } else {
            return "Pending";
        }

    }

    public void deleteStatus(String key) {
        commitStatusRepository.deleteCommitStatusesByKpackKey(key);
    }

}
