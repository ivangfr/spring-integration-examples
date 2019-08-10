package com.mycompany.fileservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileReadingMessageSource.WatchEventType;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Value("${application.inbound.path}")
    private String inboundPath;

    @Bean
    MessageChannel fileInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value = "fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource fileReadingMessageSource = new FileReadingMessageSource();
        fileReadingMessageSource.setDirectory(new File(inboundPath));
        fileReadingMessageSource.setFilter(new SimplePatternFileListFilter("*.txt"));
        fileReadingMessageSource.setUseWatchService(true);
        fileReadingMessageSource.setWatchEvents(WatchEventType.CREATE, WatchEventType.MODIFY);
        return fileReadingMessageSource;
    }

    @Bean
    @Transformer(inputChannel = "fileInputChannel", outputChannel = "transformerFileChannel")
    FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }

}
