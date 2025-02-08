package kr.co.kimga.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
public class NotificationRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Instant setLastReadAt(long userId) {
        long lastReadAt = Instant.now().toEpochMilli();
        String key = getKey(userId);

        redisTemplate.opsForValue().set(key, String.valueOf(lastReadAt));
        redisTemplate.expire(key, 90, TimeUnit.DAYS);

        return Instant.ofEpochMilli(lastReadAt);
    }

    public Instant getLastReadAt(long userId) {
        String key = getKey(userId);
        String lastReadAtStr = redisTemplate.opsForValue().get(key);

        if (lastReadAtStr == null) {
            return null;
        }

        long lastReadAt = Long.parseLong(lastReadAtStr);
        return Instant.ofEpochMilli(lastReadAt);
    }

    public String getKey(long userId) {
        return userId + ":lastReadAt";
    }
}
