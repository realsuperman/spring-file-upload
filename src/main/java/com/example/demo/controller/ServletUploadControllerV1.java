package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("/servlet/v1")
public class ServletUploadControllerV1 {
    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws IOException, ServletException {
        log.info("request={}",request);
        String itemName = request.getParameter("itemName");
        log.info("itemName={}",itemName);

        Collection<Part> parts = request.getParts();
        log.info("parts={}",parts); // 복수개로 날라오는 데이터들(멀티파트 폼 데이터)
        return "upload-form";
    }
}