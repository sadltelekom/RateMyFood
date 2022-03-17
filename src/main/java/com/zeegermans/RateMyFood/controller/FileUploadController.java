package com.zeegermans.RateMyFood.controller;

import com.zeegermans.RateMyFood.db.PictureDB;
import com.zeegermans.RateMyFood.storage.FileStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileUploadController {
    @CrossOrigin(origins = "*")
    @PostMapping("/upload/{id}")
    public String handleFileUpload(@PathVariable long id,@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        PictureDB picture = new PictureDB();

        String newFileName =  picture.createNewPictureName();

        picture.createNewPicture(newFileName,id,1);
        FileStorage fileStorage = new FileStorage();
        fileStorage.saveFile(file,newFileName);
        return "";
    }

}
