package com.mycompany.calculatorapi.integration;

import com.mycompany.calculatorapi.rest.dto.OperationRequest;
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
    void logHandler(Message<OperationRequest> message) {
        log.info("Received message\n---\nHEADERS: {};\nPAYLOAD: {}\n---", message.getHeaders(), message.getPayload());
    }

    @ServiceActivator(inputChannel = "addRouterChannel")
    public BigDecimal addHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.getA().add(operationRequest.getB());
    }

    @ServiceActivator(inputChannel = "subtractRouterChannel")
    public BigDecimal subtractHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.getA().subtract(operationRequest.getB());
    }

    @ServiceActivator(inputChannel = "divideRouterChannel")
    public BigDecimal divideHandler(@Payload OperationRequest operationRequest) {
        try {
            return operationRequest.getA().divide(operationRequest.getB(), 2, RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division by 0", e);
        }
    }

    @ServiceActivator(inputChannel = "multiplyRouterChannel")
    public BigDecimal multiplyHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.getA().multiply(operationRequest.getB());
    }
}
