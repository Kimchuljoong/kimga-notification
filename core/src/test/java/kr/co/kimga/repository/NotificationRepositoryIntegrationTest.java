package kr.co.kimga.repository;

import kr.co.kimga.IntegrationTest;
import kr.co.kimga.domain.CommentNotification;
import kr.co.kimga.domain.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static kr.co.kimga.domain.NotificationType.COMMENT;
import static org.junit.jupiter.api.Assertions.*;

class NotificationRepositoryIntegrationTest extends IntegrationTest {

    @Autowired
    private NotificationRepository sut;

    private final long userId = 2L;
    private final long postId = 3L;
    private final long writerId = 4L;
    private final long commentId = 5L;
    private final String comment = "comment";
    private final Instant now = Instant.now();
    private final Instant ninetyDaysAfter = Instant.now().plus(90, DAYS);

    @Test
    void testSave() {
        String id = "1";
        sut.save(createCommentNotification(id));
        Optional<Notification> optionalNotification = sut.findById(id);

        assertTrue(optionalNotification.isPresent());
    }

    @Test
    void testFindById() {
        String id = "2";

        sut.save(createCommentNotification(id));
        Optional<Notification> optionalNotification = sut.findById(id);

        CommentNotification notification = (CommentNotification) optionalNotification.orElseThrow();
        assertEquals(notification.getId(), id);
        assertEquals(notification.getUserId(), userId);
        assertEquals(notification.getOccurredAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getCreatedAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getLastUpdatedAt().getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.getDeletedAt().getEpochSecond(), ninetyDaysAfter.getEpochSecond());
        assertEquals(notification.getPostId(), postId);
        assertEquals(notification.getWriterId(), writerId);
        assertEquals(notification.getComment(), comment);
        assertEquals(notification.getCommentId(), commentId);
    }

    @Test
    void testDeleteById() {
        String id = "3";

        sut.save(createCommentNotification(id));
        sut.deleteById(id);
        Optional<Notification> optionalNotification = sut.findById(id);

        assertFalse(optionalNotification.isPresent());
    }

    private CommentNotification createCommentNotification(String id) {
        return new CommentNotification(id, userId, COMMENT, now, now, now, ninetyDaysAfter, postId, writerId, comment,
                commentId);
    }
}
