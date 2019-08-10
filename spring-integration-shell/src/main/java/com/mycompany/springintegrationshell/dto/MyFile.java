package com.mycompany.springintegrationshell.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MyFile {

    private String filename;
    private String content;
    private LocalDateTime lastModifiedDate;

}
