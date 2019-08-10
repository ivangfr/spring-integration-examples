package com.mycompany.fileservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document(collection = "myFiles")
public class MyFile {

    @Id
    private String filename;

    private String content;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    public MyFile(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }
}
