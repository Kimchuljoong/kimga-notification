package kr.co.kimga.task;

import kr.co.kimga.*;
import kr.co.kimga.event.LikeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeRemoveTask {

    private final NotificationGetService notificationGetService;
    private final NotificationRemoveService notificationRemoveService;
    private final NotificationSaveService notificationSaveService;

    public void processEvent(LikeEvent event) {
        Optional<Notification> optionalNotification = notificationGetService.getNotificationByTypeAndPostId(NotificationType.LIKE, event.getPostId());
        if (optionalNotification.isEmpty()) {
            log.error("No Notification with postId: {}", event.getPostId());
            return;
        }

        LikeNotification likeNotification = (LikeNotification) optionalNotification.get();
        removeLikerAndUpdateNotification(likeNotification, event);
    }

    private void removeLikerAndUpdateNotification(LikeNotification likeNotification, LikeEvent event) {
        likeNotification.removeLiker(event.getUserId(), Instant.now());

        if (likeNotification.getLikerIds().isEmpty()) {
            notificationRemoveService.deleteById(likeNotification.getId());
        } else {
            notificationSaveService.upsert(likeNotification);
        }
    }
}
