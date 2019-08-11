package com.mycompany.calculatorapi.rest;

import com.mycompany.calculatorapi.integration.IntegrationGateway;
import com.mycompany.calculatorapi.rest.dto.OperationDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final IntegrationGateway integrationGateway;

    public CalculatorController(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @PostMapping("/calculate")
    public BigDecimal calculate(@Valid @RequestBody OperationDto operationDto) {
        return integrationGateway.sendMessage(operationDto);
    }

}
