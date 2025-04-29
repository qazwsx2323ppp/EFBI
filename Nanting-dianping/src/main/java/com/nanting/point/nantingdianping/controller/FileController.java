package com.nanting.point.nantingdianping.controller;
import com.nanting.point.nantingdianping.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/file/{key}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String key) throws Exception {
        InputStream inputStream = fileService.downloadFile(key);
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", key);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bytes);
            // return ResponseEntity.ok(bytes);
    }

    @PostMapping("/file")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("filename") String filename) throws Exception {

        // Extract file extension
        String extension = "";
        int lastDotIndex = filename.lastIndexOf(".");
        if (lastDotIndex == -1) {
            extension = "";
        }
        if (lastDotIndex > 0) {
            extension = filename.substring(lastDotIndex + 1);
        }

        // Upload file and get the key
        String key = fileService.uploadFile(file.getInputStream(), extension);

        return key;
    }
}
