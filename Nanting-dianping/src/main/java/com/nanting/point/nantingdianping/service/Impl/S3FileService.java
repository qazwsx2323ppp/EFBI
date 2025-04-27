package com.nanting.point.nantingdianping.service.Impl;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.nanting.point.nantingdianping.config.S3Config;
import com.nanting.point.nantingdianping.service.FileService;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;


/**
 * @author suyiiyii
 */
@Service
@Primary
@ConditionalOnProperty(prefix = "aws.s3", name = "endpoint")
public class S3FileService implements FileService {
    private final String endpoint;
    private final String bucket;
    private final AmazonS3 s3client;

    public S3FileService(S3Config s3Config) {
        this.endpoint = s3Config.getEndpoint();
        this.bucket = s3Config.getBucketName();
        URL endpointUrl;
        try {
            endpointUrl = new URL(endpoint);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String protocol = endpointUrl.getProtocol();
        int port = endpointUrl.getPort() == -1 ? endpointUrl.getDefaultPort() : endpointUrl.getPort();
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.setSignerOverride("S3SignerType");
        clientConfig.setProtocol(Protocol.valueOf(protocol.toUpperCase()));
        // 禁用证书检查，避免https自签证书校验失败
        System.setProperty("com.amazonaws.sdk.disableCertChecking", "true");
        // 屏蔽 AWS 的 MD5 校验，避免校验导致的下载抛出异常问题
        System.setProperty("com.amazonaws.services.s3.disableGetObjectMD5Validation", "true");
        AWSCredentials awsCredentials = new BasicAWSCredentials(s3Config.getAccessKey(), s3Config.getSecretKey());
        // 创建 S3FileService 实例
        AmazonS3 s3client = new AmazonS3Client(awsCredentials, clientConfig);
        s3client.setEndpoint(endpointUrl.getHost() + ":" + port);
        s3client.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        this.s3client = s3client;
    }


    private boolean bucketExists(String bucket) {
        return s3client.doesBucketExistV2(bucket);
    }

    private boolean existObject(String bucket, String objectId) {
        return s3client.doesObjectExist(bucket, objectId);
    }


    private void upload(String bucket, String objectId, InputStream input) {
        try {
            // 创建文件上传的元数据
            ObjectMetadata meta = new ObjectMetadata();
            // 设置文件上传长度
            meta.setContentLength(input.available());
            // 上传
            s3client.putObject(bucket, objectId, input, meta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void uploadFileWithKey(String key, File readablefile)  {
        try {
            s3client.putObject(bucket, key, readablefile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String uploadFile(File readablefile, String extensionName)  {
        String key = generateKey() + extensionName;
        uploadFileWithKey(key, readablefile);
        return key;
    }


    @Override
    public void uploadFile(String key, InputStream inputStream)  {
        upload(bucket, key, inputStream);
    }

    @Override
    public String uploadFile(InputStream inputStream, String extensionName)  {
        String key = generateKey() + (extensionName != null && !extensionName.isEmpty() ? "." + extensionName : "");
        uploadFile(key, inputStream);
        return key;
    }

    @Override
    public void downloadFile(String key, File writablefile)  {
        S3Object object = s3client.getObject(bucket, key);
        try (InputStream input = object.getObjectContent();
             OutputStream output = new java.io.FileOutputStream(writablefile)) {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public InputStream downloadFile(String key)  {
        S3Object object = s3client.getObject(bucket, key);
        return object.getObjectContent();
    }

    @Override
    public String getFileUrl(String key)  {
        return s3client.getUrl(bucket, key).toString();
    }
}
