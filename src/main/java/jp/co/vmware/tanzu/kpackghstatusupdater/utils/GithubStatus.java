package jp.co.vmware.tanzu.kpackghstatusupdater.utils;

import com.fasterxml.jackson.databind.JsonNode;
import org.jose4j.json.internal.json_simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GithubStatus {

    Map<String, String> statusMap = Map.of("True", "success",
            "False", "error", "Pending", "pending");

    RestTemplate restTemplate;


    private final String githubToken;

    public GithubStatus(RestTemplateBuilder builder, @Value("#{github.token}") String githubToken) {
        this.restTemplate = builder.build();
        this.githubToken = githubToken;
    }

    // https://docs.github.com/en/rest/commits/statuses
    public void updateStatus(String url, String sha, String status) {

        String apiUrl = null;

        if (url.startsWith("https://github.com/")) {
            apiUrl = url.replace("https://github.com/", "https://api.github.com/repos/");
        }


        String targetUrl = apiUrl + "/statuses/" + sha;
        System.out.println(targetUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        headers.set("Authorization", "Bearer " + githubToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", statusMap.get(status));
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(), headers);
        restTemplate.postForObject(targetUrl, httpEntity, String.class);

    }

    private JsonNode getStatus(String url, String sha) {
        String targetUrl = url + "/statuses/" + sha;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.github+json");
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        return restTemplate.getForObject(targetUrl, JsonNode.class, httpEntity);
    }
}
