package com.ivanfranchin.fileservice.myfile;

import com.ivanfranchin.fileservice.myfile.model.MyFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyFileRepository extends MongoRepository<MyFile, String> {
}
