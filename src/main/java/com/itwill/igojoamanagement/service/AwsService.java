package com.itwill.igojoamanagement.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AwsService {

    private final String bucketName = "igojoa";

    @Autowired
    private S3Client s3Client;

    private String changeName(String UsersId, String originName) {
        return UsersId + "_" + originName;
    }

    public String uploadImage(MultipartFile image, String UsersId) {
        String originName = image.getOriginalFilename();
        String extension = originName.substring(originName.lastIndexOf("."));
        String changedName = changeName(UsersId, originName);

        try (InputStream inputStream = image.getInputStream()) {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(changedName)
                    .contentType("image/" + extension)
                    .build();

            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, image.getSize()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(changedName)).toString();
    }

    public void deleteImage(String UsersId, String originName) {
        String changedName = changeName(UsersId, originName);

        try {
            DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                    .bucket(bucketName)
                    .key(changedName)
                    .build();

            s3Client.deleteObject(deleteObjectRequest);
            System.out.println("이미지가 성공적으로 삭제되었습니다: " + changedName);
        } catch (S3Exception e) {
            System.err.println("이미지 삭제 중 오류 발생: " + e.getMessage());
            throw new RuntimeException("이미지 삭제 실패", e);
        }
    }

    public String updateImage(MultipartFile newImage, String UsersId, String oldImageName) {
        deleteImage(UsersId, oldImageName);
        return uploadImage(newImage, UsersId);
    }
}