package com.stk.book.file;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class FileStorageService {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadBasePath;

    public String saveFile(
            @NonNull MultipartFile file,
            @NonNull Integer userId) {
        String fileUploadSubPath = "users" + File.separator + userId;
        return uploadFile(file, userId, fileUploadSubPath);
    }

    private String uploadFile(MultipartFile file, Integer userId, String fileUploadSubPath) {
        String finalUploadPath = fileUploadBasePath + File.separator + fileUploadSubPath;
        File targetFolder = new File(finalUploadPath);
        if (!targetFolder.exists()) {
            boolean fileCreated = targetFolder.mkdirs();
            if (!fileCreated) {
                log.warn("Target file cannot be created.");
                return null;
            }
        }
        final String fileExtension = getFileExtension(file.getOriginalFilename());
        String targetFilePath = finalUploadPath + File.separator + System.currentTimeMillis() + fileExtension;
        Path targetPath = Paths.get(targetFilePath);
        try {
            Files.write(targetPath, file.getBytes());
            log.info("File successfully saved at location::" + targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.info("An error occured while writing file to destination::", e);
        }
        return null;
    }

    private String getFileExtension(String originalFilename) {
        if (originalFilename.isBlank() || originalFilename.isEmpty()) {
            return "";
        }
        int indexOfDot = originalFilename.lastIndexOf('.');
        if (indexOfDot == -1) {
            return "";
        }
        return originalFilename.substring(indexOfDot + 1).toLowerCase();
    }
}
