package vn.com.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.store.repository.documents.Product;

import java.util.List;

public interface ProductRepo extends MongoRepository<Product,String> {
    Product findByProductId(String productId);
    List<Product> findByNameLikeOrderBy(String name, String orderBy);
}
