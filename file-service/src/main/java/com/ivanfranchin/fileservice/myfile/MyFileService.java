package com.ivanfranchin.fileservice.myfile;

import com.ivanfranchin.fileservice.myfile.exception.MyFileNotFoundException;
import com.ivanfranchin.fileservice.myfile.model.MyFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyFileService {

    private final MyFileRepository myFileRepository;

    public MyFile getFile(String filename) throws MyFileNotFoundException {
        return myFileRepository.findById(filename)
                .orElseThrow(() -> new MyFileNotFoundException(String.format("File with name '%s' not found", filename)));
    }

    public MyFile saveFile(MyFile myFile) {
        return myFileRepository.save(myFile);
    }
}
