package kr.co.kimga;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class NotificationRepositoryMemoryImplTest {

    private final NotificationRepositoryMemoryImpl sut = new NotificationRepositoryMemoryImpl();

    @Test
    void test_save() {
        // given
        Notification notification = new Notification("1", 2L, NotificationType.LIKE, Instant.now(), Instant.now().plus(90, ChronoUnit.DAYS));

        // when
        sut.save(notification);

        // then
        Optional<Notification> findNotification = sut.findById("1");
        assertTrue(findNotification.isPresent());

    }

}