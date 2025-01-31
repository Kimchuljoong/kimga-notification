package kr.co.kimga;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> getNotification(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }
}
