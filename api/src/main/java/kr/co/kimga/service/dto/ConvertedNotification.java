package kr.co.kimga.service.dto;

import kr.co.kimga.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@AllArgsConstructor
public abstract class ConvertedNotification {
    protected String id;
    protected NotificationType type;
    protected Instant occurredAt;
    protected Instant lastUpdatedAt;
}
