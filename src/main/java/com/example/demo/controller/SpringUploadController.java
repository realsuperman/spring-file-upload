package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/spring")
public class SpringUploadController {
    @Value("${file.dir}") // application.properties에서 속성 가져옴
    private String fileDir;

    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFile(@RequestParam String itemName, @RequestParam MultipartFile file, HttpServletRequest request) throws IOException {
        //RequestParam은 MultipartFile이라는 것이 있다.이걸로 파일 객체를 받을 수 있다
        log.info("request={}",request);
        log.info("itemName={}",itemName);
        log.info("multipartFile={}",file);

        if(!file.isEmpty()){
            String fullPath = fileDir+file.getOriginalFilename();
            log.info("파일 저장 fullPath={}",fullPath);
            file.transferTo(new File(fullPath)); // 파일을 경로에 저장해줌
        }
        return "upload-form";
    }
}
