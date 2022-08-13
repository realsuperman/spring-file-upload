package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;

@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {
    @Value("${file.dir}") // application.properties에서 속성 가져옴
    private String fileDir;

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

        for (Part part : parts) {
            log.info("==== PART ====");
            log.info("name={}",part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {} : {}",headerName,part.getHeader(headerName));
            }
            // 편의 메소드
            // content-disposition; filename
            log.info("submittedFiledname={}",part.getSubmittedFileName());
            log.info("size={}",part.getSize()); // part body size

            //데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body={}",body);

            if(StringUtils.hasText(part.getSubmittedFileName())){
                String fullPath = fileDir+part.getSubmittedFileName();
                log.info("파일 저장 fullPath={}",fullPath);
                part.write(fullPath);
            }
        }
        return "upload-form";
    }
}