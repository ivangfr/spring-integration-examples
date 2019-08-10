package com.mycompany.fileservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends IOException {

    public MyFileNotFoundException(String message) {
        super(message);
    }
}
