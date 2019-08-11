package com.mycompany.springintegrationshell.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.io.File;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "gatewayChannel")
    String sendMessage(Object message);

    @Gateway(requestChannel = "writeFileChannel")
    void writeToFile(@Header(FileHeaders.FILENAME) String filename,
                     @Header(FileHeaders.ORIGINAL_FILE) File originalFile,
                     String data);

}
