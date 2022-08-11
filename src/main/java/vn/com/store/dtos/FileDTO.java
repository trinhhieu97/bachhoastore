package vn.com.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileDTO {
    private Integer id;

    private String fileName;

    private Integer fileType;

    private String url;
}
