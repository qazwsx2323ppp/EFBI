package com.nanting.point.nantingdianping.service;


import java.io.File;
import java.io.InputStream;

public interface FileService {
    void uploadFileWithKey(String key, File readablefile) ;

    String uploadFile(File readablefile, String extensionName) ;

    void uploadFile(String key, InputStream inputStream) ;

    String uploadFile(InputStream inputStream, String extensionName) ;

    void downloadFile(String key, File writablefile) ;

    InputStream downloadFile(String key) ;

    String getFileUrl(String key) ;
}
