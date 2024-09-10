package com.itwill.igojoamanagement.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsService {
    private final String bucketName = "igojoa";
    private final AmazonS3 amazonS3;

    private String changeName(String UsersId, String originName) { // 이미지 이름 중복 방지를 위해 랜덤으로 생성
        return UsersId + "_" + originName;
    }

    public  String uploadImage(MultipartFile image, String UsersId) {
        String originName = image.getOriginalFilename();
        String extension = originName.substring(originName.lastIndexOf("."));
        String changedName = changeName(UsersId, originName);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/" + extension);
        try (InputStream inputStream = image.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucketName, changedName, inputStream, objectMetadata));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return amazonS3.getUrl(bucketName, changedName).toString(); // 데이터베이스에 저장할 이미지가 저장된 주소를 반환
    }

}