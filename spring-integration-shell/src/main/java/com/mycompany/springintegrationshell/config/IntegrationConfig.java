package com.mycompany.springintegrationshell.config;

import com.mycompany.springintegrationshell.dto.CalculatorApiDto;
import com.mycompany.springintegrationshell.dto.GreetingDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.messaging.MessageChannel;

@Slf4j
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
        router.setChannelMapping(CalculatorApiDto.class.getName(), "calculatorRouterChannel");
        router.setChannelMapping(GreetingDto.class.getName(), "greetingRouterChannel");
        return router;
    }

}
