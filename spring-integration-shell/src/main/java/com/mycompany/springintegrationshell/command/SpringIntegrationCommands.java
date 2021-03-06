package com.mycompany.springintegrationshell.command;

import com.mycompany.springintegrationshell.dto.CalculatorApiDto;
import com.mycompany.springintegrationshell.dto.FileInfoDto;
import com.mycompany.springintegrationshell.dto.GreetingDto;
import com.mycompany.springintegrationshell.integration.IntegrationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.nio.file.Paths;

@RequiredArgsConstructor
@ShellComponent
public class SpringIntegrationCommands {

    @Value("${application.outbound.path}")
    private String outboundPath;

    private final IntegrationGateway integrationGateway;

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

    @ShellMethod("Get info from file")
    public String getInfoFromFile(String filename) {
        return integrationGateway.sendMessage(new FileInfoDto(filename));
    }

    @ShellMethod("Write to file")
    public void writeToFile(String filename, String content) {
        integrationGateway.writeToFile(filename, Paths.get(outboundPath).toFile(), content);
    }

}
