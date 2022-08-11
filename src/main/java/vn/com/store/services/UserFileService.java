package vn.com.store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import vn.com.store.constant.Constant;
import vn.com.store.dtos.FileDTO;
import vn.com.store.exception.BusinessException;
import vn.com.store.mapper.UserFileMapper;
import vn.com.store.repository.UserFileRepository;
import vn.com.store.repository.documents.UserFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserFileService implements IUserFileService {
    private UserFileRepository fileRepository;

    @Override
    public FileDTO getFileByFileId(String fileId) {
        Optional<UserFile> optional = fileRepository.findById(fileId);
        if(optional.isPresent()){
            return UserFileMapper.convertUserFileToFileDTO(optional.get());
        }
        throw null;
    }

    @Override
    public InputStream downloadFile(String fileId) {
        return null;
    }
    @Override
    public FileDTO uploadFile(String userId, Integer fileType, String fileName, InputStream inputStream) {
        try {
            String toPath = Constant.FILE_STORAGE + fileName;
            String extension = "";
            writeFile(toPath, inputStream);
            UserFile userFile = new UserFile(null, fileName, extension, userId, new Date(), toPath);
            this.fileRepository.save(userFile);
            return UserFileMapper.convertUserFileToFileDTO(userFile);
        }
        catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    private void writeFile(String toPath, InputStream inputStream) {
        File targetFile = new File(toPath);
        try (FileOutputStream outputStream = new FileOutputStream(targetFile)){
            FileCopyUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFile(String fileId) {
        if (fileRepository.existsById(fileId)) {
            Optional<UserFile> fileOptional = fileRepository.findById(fileId);
            if (fileOptional.isPresent()) {
                UserFile userFile = fileOptional.get();
                deleteFileFromPath(userFile.getPathUrl());
                fileRepository.deleteById(fileId);
            }

        }
    }

    private void deleteFileFromPath(String fromPath) {
        File file = new File(fromPath);
        if(file.exists()){
            file.delete();
        }
    }
}
