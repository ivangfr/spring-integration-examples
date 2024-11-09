package com.ivanfranchin.springintegrationshell.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class FileServiceClientConfig {

    @Value("${file-service.url}")
    private String fileServiceUrl;

    @Bean
    public FileServiceClient fileServiceClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl(fileServiceUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(FileServiceClient.class);
    }
}