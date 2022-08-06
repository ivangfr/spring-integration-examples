package com.ivanfranchin.springintegrationshell.command;

import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
import com.ivanfranchin.springintegrationshell.dto.FileInfoRequest;
import com.ivanfranchin.springintegrationshell.dto.GreetingRequest;
import com.ivanfranchin.springintegrationshell.integration.IntegrationGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.math.BigDecimal;
import java.nio.file.Paths;

@ShellComponent
public class SpringIntegrationCommands {

    private final IntegrationGateway integrationGateway;

    public SpringIntegrationCommands(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @Value("${application.outbound.path}")
    private String outboundPath;


    @ShellMethod("Add two numbers")
    public String add(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.ADD, a, b));
    }

    @ShellMethod("Subtract two numbers")
    public String subtract(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.SUBTRACT, a, b));
    }

    @ShellMethod("Divide two numbers")
    public String divide(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.DIVIDE, a, b));
    }

    @ShellMethod("Multiply two numbers")
    public String multiply(BigDecimal a, BigDecimal b) {
        return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.MULTIPLY, a, b));
    }

    @ShellMethod("Greet someone")
    public String greet(@ShellOption(defaultValue = "World") String name) {
        return integrationGateway.sendMessage(new GreetingRequest(name));
    }

    @ShellMethod("Get info from file")
    public String getInfoFromFile(String filename) {
        return integrationGateway.sendMessage(new FileInfoRequest(filename));
    }

    @ShellMethod("Write to file")
    public void writeToFile(String filename, String content) {
        integrationGateway.writeToFile(filename, Paths.get(outboundPath).toFile(), content);
    }
}
