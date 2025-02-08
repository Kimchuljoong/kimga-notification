package kr.co.kimga.service;

import kr.co.kimga.domain.Notification;
import kr.co.kimga.repository.NotificationRepository;
import kr.co.kimga.domain.NotificationType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationGetService {

    private final NotificationRepository repository;

    public Optional<Notification> getNotificationByTypeAndCommentId(NotificationType type, Long commentId) {
        return repository.findByTypeAndCommentId(type, commentId);
    }

    public Optional<Notification> getNotificationByTypeAndPostId(NotificationType type, Long postId) {
        return repository.findByTypeAndPostId(type, postId);
    }

    public Instant getLatestUpdatedAt(long userId) {
        Optional<Notification> notification = repository.findFirstByUserIdOrderByLastUpdatedAtDesc(userId);

        if (notification.isEmpty()) {
            return null;
        }

        return notification.get().getLastUpdatedAt();
    }

}
