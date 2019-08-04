package com.mycompany.springintegrationshell.service;

import com.mycompany.springintegrationshell.dto.GreetingDto;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class GreetingIntegrationService {

    @ServiceActivator(inputChannel = "greetingRouterChannel")
    public String greetingServiceActivator(@Payload GreetingDto greetingDto) {
        String greeting;
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.of(12, 0))) {
            greeting = "Good morning";
        } else if (now.isBefore(LocalTime.of(18, 0))) {
            greeting = "Good afternoon";
        } else {
            greeting = "Good evening";
        }
        return String.format("%s %s!", greeting, greetingDto.getName());
    }

}
