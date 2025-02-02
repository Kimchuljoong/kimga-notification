package kr.co.kimga.task;

import kr.co.kimga.FollowNotification;
import kr.co.kimga.NotificationIdGenerator;
import kr.co.kimga.NotificationSaveService;
import kr.co.kimga.NotificationType;
import kr.co.kimga.event.FollowEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowAddTask {

    private final NotificationSaveService notificationSaveService;

    public void processEvent(FollowEvent event) {
        notificationSaveService.insert(createFollowNotification(event));
    }

    private static FollowNotification createFollowNotification(FollowEvent event) {
        Instant now = Instant.now();

        return new FollowNotification(
                NotificationIdGenerator.generate(),
                event.getTargetUserId(),
                NotificationType.FOLLOW,
                event.getCreatedAt(),
                now,
                now,
                now.plus(90, ChronoUnit.DAYS),
                event.getUserId()
        );
    }

}
