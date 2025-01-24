package kr.co.kimga;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootApplication
@SpringBootTest
class NotificationRepositoryMemoryImplTest {

    @Autowired
    private NotificationRepository sut;

    private final Instant now = Instant.now();
    private final Instant deletedAt = Instant.now().plus(90, ChronoUnit.DAYS);

    @Test
    void test_save() {
        // given
        Notification notification = new Notification("1", 2L, NotificationType.LIKE, now, deletedAt);

        // when
        sut.save(notification);

        // then
        Optional<Notification> findNotification = sut.findById("1");
        assertTrue(findNotification.isPresent());

    }

    @Test
    void test_find_by_id() {
        // given
        sut.save(new Notification("2", 2L, NotificationType.LIKE, now, deletedAt));
        Optional<Notification> optionalNotification = sut.findById("2");

        // when
        Notification notification = optionalNotification.orElseThrow();

        // then
        assertEquals(notification.id, "2");
        assertEquals(notification.userId, 2L);
        assertEquals(notification.createdAt.getEpochSecond(), now.getEpochSecond());
        assertEquals(notification.deletedAt.getEpochSecond(), deletedAt.getEpochSecond());
    }

    @Test
    void test_delete_by_id() {
        // given
        sut.save(new Notification("3", 3L, NotificationType.LIKE, now, deletedAt));
        sut.deleteById("3");

        // when
        Optional<Notification> optionalNotification = sut.findById("3");

        // then
        assertFalse(optionalNotification.isPresent());

    }

}