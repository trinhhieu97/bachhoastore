package vn.com.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.store.repository.documents.User;

public interface UserRepo extends MongoRepository<User, String> {
    public User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
