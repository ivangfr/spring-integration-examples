package com.mycompany.calculatorapi.integration;

import com.mycompany.calculatorapi.rest.dto.OperationDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.ExpressionEvaluatingRouter;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    MessageChannel gatewayChannel() {
        return new PublishSubscribeChannel();
    }

    @Router(inputChannel = "gatewayChannel")
    @Bean
    ExpressionEvaluatingRouter expressionEvaluatingRouter() {
        ExpressionEvaluatingRouter router = new ExpressionEvaluatingRouter("payload.operation");
        router.setChannelMapping(OperationDto.Type.ADD.name(), "addRouterChannel");
        router.setChannelMapping(OperationDto.Type.SUBTRACT.name(), "subtractRouterChannel");
        router.setChannelMapping(OperationDto.Type.DIVIDE.name(), "divideRouterChannel");
        router.setChannelMapping(OperationDto.Type.MULTIPLY.name(), "multiplyRouterChannel");
        return router;
    }

}
