package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileUploadController {

    @PostMapping("/file-upload")
    public String fileUpload(@RequestParam("fileUpload") MultipartFile fileUpload, Model model) throws IOException {

        InputStream fis = fileUpload.getInputStream();
        System.out.println(fis);
        System.out.println(fileUpload.getOriginalFilename());
        model.addAttribute("fileName", fileUpload.getOriginalFilename());
        return "home";
    }
}
