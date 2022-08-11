package vn.com.store.mapper;
import org.springframework.stereotype.Component;
import vn.com.store.dtos.FileDTO;
import vn.com.store.repository.documents.UserFile;
@Component
public class UserFileMapper {
    public static FileDTO convertUserFileToFileDTO(UserFile userFile) {
        FileDTO fileDTO = new FileDTO();
        fileDTO.setFileName(userFile.getName());
        fileDTO.setFileName(userFile.getType());
        fileDTO.setFileName(userFile.getPathUrl());
        return fileDTO;
    }
}
