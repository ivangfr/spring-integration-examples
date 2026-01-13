package com.ivanfranchin.springintegrationshell.command;

import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
import com.ivanfranchin.springintegrationshell.dto.FileInfoRequest;
import com.ivanfranchin.springintegrationshell.dto.GreetingRequest;
import com.ivanfranchin.springintegrationshell.integration.IntegrationGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.Option;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.nio.file.Paths;

@Component
public class SpringIntegrationCommands {

    private final IntegrationGateway integrationGateway;

    public SpringIntegrationCommands(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    @Value("${application.outbound.path}")
    private String outboundPath;


    @Command(name = "add", description = "Add two numbers", group = "Calculator API")
    public String add(@Option(longName = "a", required = true) BigDecimal a,
                      @Option(longName = "b", required = true) BigDecimal b) {
        try {
            return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.ADD, a, b));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "subtract", description = "Subtract two numbers", group = "Calculator API")
    public String subtract(@Option(longName = "a", required = true) BigDecimal a,
                           @Option(longName = "b", required = true) BigDecimal b) {
        try {
            return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.SUBTRACT, a, b));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "divide", description = "Divide two numbers", group = "Calculator API")
    public String divide(@Option(longName = "a", required = true) BigDecimal a,
                         @Option(longName = "b", required = true) BigDecimal b) {
        try {
            return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.DIVIDE, a, b));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "multiply", description = "Multiply two numbers", group = "Calculator API")
    public String multiply(@Option(longName = "a", required = true) BigDecimal a,
                           @Option(longName = "b", required = true) BigDecimal b) {
        try {
            return integrationGateway.sendMessage(new CalculatorApiRequest(CalculatorApiRequest.Type.MULTIPLY, a, b));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "greeting", description = "Greet someone", group = "Greeting Service")
    public String greet(@Option(longName = "name", defaultValue = "World") String name) {
        try {
            return integrationGateway.sendMessage(new GreetingRequest(name));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "get-info-from-file", description = "Get info from file", group = "File Service")
    public String getInfoFromFile(@Option(longName = "filename", required = true) String filename) {
        try {
            return integrationGateway.sendMessage(new FileInfoRequest(filename));
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    @Command(name = "write-to-file", description = "Write to file", group = "File Service")
    public String writeToFile(@Option(longName = "filename", required = true) String filename,
                              @Option(longName = "content", required = true) String content) {
        try {
            integrationGateway.writeToFile(filename, Paths.get(outboundPath).toFile(), content);
            return "Content written to file successfully.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
