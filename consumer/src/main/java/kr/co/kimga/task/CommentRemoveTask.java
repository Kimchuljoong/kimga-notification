package kr.co.kimga.task;

import kr.co.kimga.*;
import kr.co.kimga.event.CommentEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentRemoveTask {

    private final PostClient postClient;

    private final NotificationGetService getService;

    private final NotificationRemoveService removeService;

    public void processEvent(CommentEvent event) {
        Post post = postClient.getPost(event.getPostId());
        if (Objects.equals(post.getUserId(), event.getUserId())) {
            return;
        }

        getService.getNotification(NotificationType.COMMENT, event.getCommentId())
                .ifPresentOrElse(notification -> removeService.deleteById(notification.getId()),
                        () -> log.error("Notification Not Found")
                );
    }
}
