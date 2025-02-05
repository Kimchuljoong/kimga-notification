package kr.co.kimga.service;

import kr.co.kimga.domain.Notification;
import kr.co.kimga.repository.NotificationRepository;
import kr.co.kimga.service.dto.GetUserNotificationByPivotResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListService {

    private final NotificationRepository mNotificationRepository;

    private static final int PAGE_SIZE = 20;

    public GetUserNotificationByPivotResult getUserNotificationsByPivot(Long userId, Instant occurredAt) {

        Slice<Notification> result;

        if (occurredAt == null) {
            result = mNotificationRepository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            result = mNotificationRepository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }

        return new GetUserNotificationByPivotResult(result.toList(), result.hasNext());
    }

}
