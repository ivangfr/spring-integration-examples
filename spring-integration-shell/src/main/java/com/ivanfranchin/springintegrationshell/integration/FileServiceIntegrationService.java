package com.ivanfranchin.springintegrationshell.integration;

import com.ivanfranchin.springintegrationshell.dto.FileInfoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FileServiceIntegrationService {

    private final RestTemplate restTemplate;

    public FileServiceIntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${file-service.url}")
    private String fileServiceUrl;

    @ServiceActivator(inputChannel = "fileInfoRouterChannel")
    public String fileInfoHandler(@Payload FileInfoRequest fileInfoRequest) {
        String url = String.format("%s/%s", fileServiceUrl, fileInfoRequest.filename());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }
}
