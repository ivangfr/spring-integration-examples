package com.mycompany.fileservice.integration;

import com.mycompany.fileservice.model.MyFile;
import com.mycompany.fileservice.service.MyFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class IntegrationService {

    private final MyFileService myFileService;

    @ServiceActivator(inputChannel = "transformerFileChannel")
    void logHandler(Message<String> message) {
        log.info("Received message\n---\nHEADERS: {};\nPAYLOAD: {}\n---", message.getHeaders(), message.getPayload());

        String filename = (String) message.getHeaders().get(FileHeaders.FILENAME);
        String content = message.getPayload();
        myFileService.saveFile(new MyFile(filename, content));
    }

}
