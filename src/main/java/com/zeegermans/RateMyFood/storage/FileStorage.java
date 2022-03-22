package com.zeegermans.RateMyFood.storage;


import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileStorage {
    public void saveFile(MultipartFile mpfile, String filename) {

        String path = "src/main/recipe/images/";
        File filePath = new File(path);
        File file = new File(filePath.getAbsolutePath(), filename);


        try {
            mpfile.transferTo(file);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
