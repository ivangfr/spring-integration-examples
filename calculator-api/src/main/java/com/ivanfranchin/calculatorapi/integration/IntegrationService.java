package com.ivanfranchin.calculatorapi.integration;

import com.ivanfranchin.calculatorapi.calculator.OperationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class IntegrationService {

    private static final Logger log = LoggerFactory.getLogger(IntegrationService.class);

    @ServiceActivator(inputChannel = "gatewayChannel")
    void logHandler(Message<OperationRequest> message) {
        log.info("Received message\n---\nHEADERS: {};\nPAYLOAD: {}\n---", message.getHeaders(), message.getPayload());
    }

    @ServiceActivator(inputChannel = "addRouterChannel")
    public BigDecimal addHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.a().add(operationRequest.b());
    }

    @ServiceActivator(inputChannel = "subtractRouterChannel")
    public BigDecimal subtractHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.a().subtract(operationRequest.b());
    }

    @ServiceActivator(inputChannel = "divideRouterChannel")
    public BigDecimal divideHandler(@Payload OperationRequest operationRequest) {
        try {
            return operationRequest.a().divide(operationRequest.b(), 2, RoundingMode.HALF_EVEN);
        } catch (ArithmeticException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Division by 0", e);
        }
    }

    @ServiceActivator(inputChannel = "multiplyRouterChannel")
    public BigDecimal multiplyHandler(@Payload OperationRequest operationRequest) {
        return operationRequest.a().multiply(operationRequest.b());
    }
}
