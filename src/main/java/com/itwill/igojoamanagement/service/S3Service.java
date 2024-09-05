package com.itwill.igojoamanagement.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private String bucketName = "igojoa";
    private final AmazonS3 amazonS3;

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = generateFileName(file); // 고유한 파일 이름 생성
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // S3에 파일 업로드
        amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);

        // 업로드된 파일의 URL 반환
        return amazonS3.getUrl(bucketName, fileName).toString();
    }

    // 고유한 파일 이름 생성
    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename().replaceAll(" ", "_");
    }

}
