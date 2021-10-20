package com.mycompany.springintegrationshell.integration;

import com.mycompany.springintegrationshell.dto.GreetingRequest;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class GreetingIntegrationService {

    @ServiceActivator(inputChannel = "greetingRouterChannel")
    public String greetingHandler(@Payload GreetingRequest greetingRequest) {
        String greeting;
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.of(12, 0))) {
            greeting = "Good morning";
        } else if (now.isBefore(LocalTime.of(18, 0))) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good evening";
        }
        return String.format("%s %s!", greeting, greetingRequest.getName());
    }
}
