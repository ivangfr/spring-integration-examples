package com.mycompany.fileservice.service;

import com.mycompany.fileservice.exception.MyFileNotFoundException;
import com.mycompany.fileservice.model.MyFile;

public interface MyFileService {

    MyFile getFile(String filename) throws MyFileNotFoundException;

    MyFile saveFile(MyFile myFile);

}
