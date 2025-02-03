package kr.co.kimga.service;

import kr.co.kimga.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationListService {

    private final NotificationRepository mNotificationRepository;

    private static final int PAGE_SIZE = 20;

    public void getUserNotificationsByPivot(Long userId, Instant occurredAt) {
        if (occurredAt == null) {
            mNotificationRepository.findAllByUserIdOrderByOccurredAtDesc(userId, PageRequest.of(0, PAGE_SIZE));
        } else {
            mNotificationRepository.findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(userId, occurredAt, PageRequest.of(0, PAGE_SIZE));
        }
    }

}
