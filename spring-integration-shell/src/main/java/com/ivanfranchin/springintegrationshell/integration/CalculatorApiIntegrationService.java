package com.ivanfranchin.springintegrationshell.integration;

import com.ivanfranchin.springintegrationshell.client.CalculatorApiClient;
import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CalculatorApiIntegrationService {

    private final CalculatorApiClient calculatorApiClient;

    public CalculatorApiIntegrationService(CalculatorApiClient calculatorApiClient) {
        this.calculatorApiClient = calculatorApiClient;
    }

    @ServiceActivator(inputChannel = "calculatorRouterChannel")
    public String calculatorHandler(@Payload CalculatorApiRequest calculatorApiRequest) {
        return calculatorApiClient.calculate(calculatorApiRequest);
    }
}
