package com.ivanfranchin.calculatorapi.integration;

import com.ivanfranchin.calculatorapi.rest.dto.OperationRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.math.BigDecimal;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "gatewayChannel")
    BigDecimal sendMessage(OperationRequest operationRequest);
}
