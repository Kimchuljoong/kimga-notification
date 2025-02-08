package kr.co.kimga.service;

import kr.co.kimga.repository.NotificationRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class LastReadAtService {

    private final NotificationRedisRepository notificationRedisRepository;

    public Instant setLastReadAt(long userId) {
        return notificationRedisRepository.setLastReadAt(userId);
    }

    public Instant getLastReadAt(long userId) {
        return notificationRedisRepository.getLastReadAt(userId);
    }
}
