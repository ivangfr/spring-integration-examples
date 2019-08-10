package com.mycompany.fileservice.repository;

import com.mycompany.fileservice.model.MyFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyFileRepository extends MongoRepository<MyFile, String> {
}
