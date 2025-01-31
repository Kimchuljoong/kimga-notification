package kr.co.kimga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Document("notifications")
public abstract class Notification {
    private String id;
    private Long userId;
    private NotificationType type;
    private Instant occurredAt;
    private Instant createdAt;
    private Instant lastUpdatedAt;
    private Instant deletedAt;

}
