package vn.com.store.repository.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "userFile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFile {
    @Id
    ObjectId id;
    String name;
    String type; // image/video/thumbnail
    String uploadBy;
    Date createdAt;
    String pathUrl;
}
