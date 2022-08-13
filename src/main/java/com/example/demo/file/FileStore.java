package com.example.demo.file;

import com.example.demo.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    public String getFullPath(String filename){
        return fileDir+filename;
    }

    public UploadFile storeFile(MultipartFile multiPartFile) throws IOException {
        if(multiPartFile.isEmpty()) return null;
        String name = multiPartFile.getOriginalFilename();
        // image.png로 날라오면 서버에는 uuid를 사용하여 저장할 것임
        String storeFileName = createStoreFileName(name);
        multiPartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(name,storeFileName);
    }

    private String createStoreFileName(String name) {
        return UUID.randomUUID().toString()+"."+extracted(name);
    }

    private String extracted(String name) {
        int pos = name.lastIndexOf(".");
        return name.substring(pos+1);
    }

}
