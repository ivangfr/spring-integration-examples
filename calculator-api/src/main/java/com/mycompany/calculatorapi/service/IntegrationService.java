package com.mycompany.calculatorapi.service;

import com.mycompany.calculatorapi.rest.dto.OperationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Component
public class IntegrationService {

    @ServiceActivator(inputChannel = "gatewayChannel")
    void logServiceActivator(Message<OperationDto> message) {
        log.info("Received message\n---\nHEADERS: {};\nPAYLOAD: {}\n---", message.getHeaders(), message.getPayload());
    }

    @ServiceActivator(inputChannel = "addRouterChannel")
    public BigDecimal addServiceActivator(@Payload OperationDto operationDto) {
        return operationDto.getA().add(operationDto.getB());
    }

    @ServiceActivator(inputChannel = "subtractRouterChannel")
    public BigDecimal subtractServiceActivator(@Payload OperationDto operationDto) {
        return operationDto.getA().subtract(operationDto.getB());
    }

    @ServiceActivator(inputChannel = "divideRouterChannel")
    public BigDecimal divideServiceActivator(@Payload OperationDto operationDto) {
        try {
            return operationDto.getA().divide(operationDto.getB(), 2, RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division by 0", e);
        }
    }

    @ServiceActivator(inputChannel = "multiplyRouterChannel")
    public BigDecimal multiplyServiceActivator(@Payload OperationDto operationDto) {
        return operationDto.getA().multiply(operationDto.getB());
    }

}
