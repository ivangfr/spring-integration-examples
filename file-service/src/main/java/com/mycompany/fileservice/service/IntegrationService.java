package com.mycompany.fileservice.service;

import com.mycompany.fileservice.model.MyFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IntegrationService {

    private final MyFileService myFileService;

    public IntegrationService(MyFileService myFileService) {
        this.myFileService = myFileService;
    }

    @ServiceActivator(inputChannel = "transformerFileChannel")
    void logHandler(Message<String> message) {
        log.info("Received message\n---\nHEADERS: {};\nPAYLOAD: {}\n---", message.getHeaders(), message.getPayload());

        String filename = (String) message.getHeaders().get(FileHeaders.FILENAME);
        String content = message.getPayload();
        myFileService.saveFile(new MyFile(filename, content));
    }

}
