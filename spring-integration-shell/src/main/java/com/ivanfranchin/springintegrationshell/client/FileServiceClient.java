package com.ivanfranchin.springintegrationshell.client;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("/api/files")
public interface FileServiceClient {

    @GetExchange("/{filename}")
    String getFile(@PathVariable String filename);
}
