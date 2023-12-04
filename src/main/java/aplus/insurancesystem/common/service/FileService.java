package aplus.insurancesystem.common.service;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(MultipartFile file, String filePath);

    InputStream downloadFile(String filePath);
}
