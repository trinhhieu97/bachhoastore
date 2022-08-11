package vn.com.store.repository.documents;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    ObjectId id;
    String name;
    String type;
    String color;
    String size;
    String brand;
    String description;
    @Indexed(unique = true)
    String productId;
    String ownerBy;
    Date createdAt;
    Date updatedAt;
}
