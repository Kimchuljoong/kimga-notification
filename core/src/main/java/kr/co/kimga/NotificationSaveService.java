package kr.co.kimga;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationSaveService {

    private final NotificationRepository repository;

    public void insert(Notification notification) {
        Notification insertedNotification = repository.insert(notification);
        log.info("inserted: {}", insertedNotification);
    }
}
