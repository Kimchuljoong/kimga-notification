package kr.co.kimga;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    Optional<Notification> findById(String id);

    Notification save(Notification notification);

    void deleteById(String id);
}
