package aplus.insurancesystem.common.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional(readOnly = true)
public class JavaFileService implements FileService{

    private final String FILE_PATH;

    public JavaFileService(@Value("${spring.profiles.active}") String activeProfile) {
        if (activeProfile.equals("test")) {
            FILE_PATH = "/home/worker/files/";
        } else {
            FILE_PATH = System.getProperty("user.dir") + "/files/";
        }
        log.info("FILE_PATH: {}", FILE_PATH);
    }

    @Override
    public void uploadFile(MultipartFile file, String filePath) {
        try {
            String finalFilePath = FILE_PATH + filePath;
            makeDirectoryIfNotExist(finalFilePath);
            file.transferTo(new File(finalFilePath));
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.UPLOAD_FILE_ERROR, e);
        }
    }

    @Override
    public InputStream downloadFile(String filePath) {
        try {
            return new File(FILE_PATH + filePath).toURI().toURL().openStream();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.DOWNLOAD_FILE_ERROR, e);
        }
    }

    private void makeDirectoryIfNotExist(String filePath) {
        File directory = new File(filePath);
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new BusinessException(ErrorCode.UPLOAD_FILE_ERROR);
            }
        }
    }
}
