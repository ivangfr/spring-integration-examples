package com.mycompany.springintegrationshell.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "gatewayChannel")
    String sendMessage(Object message);

}
