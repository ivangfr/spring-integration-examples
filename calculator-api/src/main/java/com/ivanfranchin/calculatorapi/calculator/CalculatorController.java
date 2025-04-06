package com.ivanfranchin.calculatorapi.calculator;

import com.ivanfranchin.calculatorapi.integration.IntegrationGateway;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final IntegrationGateway integrationGateway;

    public CalculatorController(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @PostMapping("/calculate")
    public BigDecimal calculate(@Valid @RequestBody OperationRequest operationRequest) {
        return integrationGateway.sendMessage(operationRequest);
    }
}
