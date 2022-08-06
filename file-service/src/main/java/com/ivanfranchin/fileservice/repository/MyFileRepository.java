package com.ivanfranchin.fileservice.repository;

import com.ivanfranchin.fileservice.model.MyFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends MongoRepository<MyFile, String> {
}
