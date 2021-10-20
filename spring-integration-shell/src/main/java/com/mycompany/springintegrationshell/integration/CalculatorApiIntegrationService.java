package com.mycompany.springintegrationshell.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.springintegrationshell.dto.CalculatorApiRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class CalculatorApiIntegrationService {

    @Value("${calculator-api.url}")
    private String calculatorApiUrl;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @ServiceActivator(inputChannel = "calculatorRouterChannel")
    public String calculatorHandler(@Payload CalculatorApiRequest calculatorApiRequest) throws JsonProcessingException {
        String calculatorApiRequestStr = objectMapper.writeValueAsString(calculatorApiRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(calculatorApiRequestStr, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(calculatorApiUrl, request, String.class);
        return response.getBody();
    }
}
