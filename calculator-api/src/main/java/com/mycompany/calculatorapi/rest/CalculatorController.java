package com.mycompany.calculatorapi.rest;

import com.mycompany.calculatorapi.integration.IntegrationGateway;
import com.mycompany.calculatorapi.rest.dto.OperationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final IntegrationGateway integrationGateway;

    @PostMapping("/calculate")
    public BigDecimal calculate(@Valid @RequestBody OperationRequest operationRequest) {
        return integrationGateway.sendMessage(operationRequest);
    }
}
