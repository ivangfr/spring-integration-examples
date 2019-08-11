package com.mycompany.calculatorapi.integration;

import com.mycompany.calculatorapi.rest.dto.OperationDto;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.math.BigDecimal;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "gatewayChannel")
    BigDecimal sendMessage(OperationDto operationDto);

}
