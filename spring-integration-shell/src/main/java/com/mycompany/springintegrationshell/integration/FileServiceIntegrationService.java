package com.mycompany.springintegrationshell.integration;

import com.mycompany.springintegrationshell.dto.FileInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class FileServiceIntegrationService {

    @Value("${file-service.url}")
    private String fileServiceUrl;

    private final RestTemplate restTemplate;

    @ServiceActivator(inputChannel = "fileInfoRouterChannel")
    public String fileInfoHandler(@Payload FileInfoDto fileContentDto) {
        String url = String.format("%s/%s", fileServiceUrl, fileContentDto.getFilename());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

}
