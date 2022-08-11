package vn.com.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.store.repository.documents.UserFile;

public interface UserFileRepository extends MongoRepository<UserFile, String> {
}
