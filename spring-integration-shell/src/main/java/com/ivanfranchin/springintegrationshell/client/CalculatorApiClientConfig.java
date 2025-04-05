package com.ivanfranchin.springintegrationshell.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class CalculatorApiClientConfig {

    @Value("${calculator-api.url}")
    private String calculatorApiUrl;

    @Bean
    CalculatorApiClient calculatorApiClient(RestClient.Builder builder) {
        RestClient restClient = builder.baseUrl(calculatorApiUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(CalculatorApiClient.class);
    }
}