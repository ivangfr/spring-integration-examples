package com.mycompany.fileservice.repository;

import com.mycompany.fileservice.model.MyFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends MongoRepository<MyFile, String> {
}
