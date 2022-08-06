package com.ivanfranchin.fileservice.service;

import com.ivanfranchin.fileservice.exception.MyFileNotFoundException;
import com.ivanfranchin.fileservice.model.MyFile;

public interface MyFileService {

    MyFile getFile(String filename) throws MyFileNotFoundException;

    MyFile saveFile(MyFile myFile);
}
