package com.mycompany.springintegrationshell.command;

import com.mycompany.springintegrationshell.dto.CalculatorApiDto;
import com.mycompany.springintegrationshell.dto.GreetingDto;
import com.mycompany.springintegrationshell.gateway.IntegrationGateway;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;

@ShellComponent
public class SpringIntegrationCommands {

    private final IntegrationGateway integrationGateway;

    public SpringIntegrationCommands(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @ShellMethod("Add two numbers")
    public String add(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiDto(CalculatorApiDto.Type.ADD, a, b));
    }

    @ShellMethod("Subtract two numbers")
    public String subtract(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiDto(CalculatorApiDto.Type.SUBTRACT, a, b));
    }

    @ShellMethod("Divide two numbers")
    public String divide(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiDto(CalculatorApiDto.Type.DIVIDE, a, b));
    }

    @ShellMethod("Multiply two numbers")
    public String multiply(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiDto(CalculatorApiDto.Type.MULTIPLY, a, b));
    }

    @ShellMethod("Greet someone")
    public String greet(@ShellOption(defaultValue = "World") String name) {
        return integrationGateway.sendMessage(new GreetingDto(name));
    }

}
