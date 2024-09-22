package com.sathvik.MusicData.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.sathvik.MusicData.constants.Secrets.KEEP_ALIVE_SERVER_URL;

@Component
@AllArgsConstructor
@Slf4j
public class KeepAliveJob {
    private RestTemplate restTemplate;

    @Scheduled(fixedRate = 30000)
    public void sendKeepAliveRequest() {
        try {
            String response = this.restTemplate.getForObject(KEEP_ALIVE_SERVER_URL + "/keep-alive", String.class);
            log.info("Response from keep-alive-server : " + response);
        }
        catch (RestClientException e) {
            log.info(e.getMessage());
        }
    }
}
