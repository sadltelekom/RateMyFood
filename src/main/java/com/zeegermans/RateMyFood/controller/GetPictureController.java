package com.zeegermans.RateMyFood.controller;



import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.File;
import java.io.FileInputStream;



@Controller
public class GetPictureController {



    @CrossOrigin(origins = "*")
    @GetMapping("/recipes/images/{filename}")
    public ResponseEntity<byte[]> getPicture(@PathVariable String filename) {

        String path = "src/main/recipe/images/";

        File filePath = new File(path);
        File file = new File(filePath.getAbsolutePath(),filename);
        FileInputStream fileInputStream = null;

        byte[] bFile = new byte[(int) file.length()];
        try
        {
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(bFile.length);
        return new ResponseEntity<byte[]>(bFile, headers, HttpStatus.OK);

        }
}
