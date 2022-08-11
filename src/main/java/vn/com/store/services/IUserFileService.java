package vn.com.store.services;

import vn.com.store.dtos.FileDTO;

import java.io.InputStream;

public interface IUserFileService {
    FileDTO getFileByFileId(String fileId);

    InputStream downloadFile(String fileId);

    FileDTO uploadFile(String name, Integer fileType, String fileName, InputStream input);

    void deleteFile(String id);
}

