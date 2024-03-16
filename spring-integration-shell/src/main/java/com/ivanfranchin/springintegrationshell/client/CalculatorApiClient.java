package com.ivanfranchin.springintegrationshell.client;

import com.ivanfranchin.springintegrationshell.dto.CalculatorApiRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/calculate")
public interface CalculatorApiClient {

    @PostExchange
    String calculate(@RequestBody CalculatorApiRequest calculatorApiRequest);
}
