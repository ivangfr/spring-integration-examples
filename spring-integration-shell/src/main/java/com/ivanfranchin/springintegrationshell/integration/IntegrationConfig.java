package com.ivanfranchin.springintegrationshell.integration;

import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
import com.ivanfranchin.springintegrationshell.dto.FileInfoRequest;
import com.ivanfranchin.springintegrationshell.dto.GreetingRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    MessageChannel gatewayChannel() {
        return new PublishSubscribeChannel();
    }

    @Router(inputChannel = "gatewayChannel")
    @Bean
    PayloadTypeRouter payloadTypeRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(CalculatorApiRequest.class.getName(), "calculatorRouterChannel");
        router.setChannelMapping(GreetingRequest.class.getName(), "greetingRouterChannel");
        router.setChannelMapping(FileInfoRequest.class.getName(), "fileInfoRouterChannel");
        return router;
    }

    @ServiceActivator(inputChannel = "writeFileChannel")
    @Bean
    MessageHandler fileWritingMessageHandler() {
        Expression directoryExpression = new SpelExpressionParser().parseExpression("headers.file_originalFile");
        FileWritingMessageHandler fileWritingMessageHandler = new FileWritingMessageHandler(directoryExpression);
        fileWritingMessageHandler.setFileExistsMode(FileExistsMode.APPEND);
        fileWritingMessageHandler.setExpectReply(false);
        return fileWritingMessageHandler;
    }
}
