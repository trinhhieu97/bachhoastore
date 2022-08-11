package vn.com.store.repository.documents;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    ObjectId id;
    String name;
    String birthday;
    @Indexed(unique = true)
    String email;
    String password;
    String mobile;
    String description;
    String avatarUrl;
}
