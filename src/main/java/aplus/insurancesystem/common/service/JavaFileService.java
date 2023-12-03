package aplus.insurancesystem.common.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import aplus.insurancesystem.common.exception.BusinessException;
import aplus.insurancesystem.common.exception.ErrorCode;

@Service
@Transactional(readOnly = true)
public class JavaFileService implements FileService{

    private static final String FILE_PATH = System.getProperty("user.dir") + "/files/";

    @Override
    public void uploadFile(MultipartFile file, String filePath) {
        try {
            file.transferTo(new File(FILE_PATH + filePath));
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
}
