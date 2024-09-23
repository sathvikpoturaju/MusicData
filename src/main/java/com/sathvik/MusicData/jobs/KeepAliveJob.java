package com.sathvik.MusicData.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.sathvik.MusicData.constants.AppConstants.KEEP_ALIVE_SERVER_URL;

@Component
@AllArgsConstructor
@Slf4j
public class KeepAliveJob {
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 30000)
    public void sendKeepAliveRequest() {
        try {
            HttpEntity<?> entity = HttpEntity.EMPTY;
            ResponseEntity<String> response = this.restTemplate.exchange(
                KEEP_ALIVE_SERVER_URL + "/keep-alive",
                HttpMethod.GET,
                entity,
                String.class
            );
            log.info("Response from keep-alive-server : " + response.getBody());
        } catch (RestClientException e) {
            log.info(e.getMessage());
        }
    }
}
