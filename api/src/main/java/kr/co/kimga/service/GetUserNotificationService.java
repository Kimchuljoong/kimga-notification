package kr.co.kimga.service;

import kr.co.kimga.service.dto.GetUserNotificationByPivotResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUserNotificationService {

    private final NotificationListService notificationListService;

    public void getUserNotificationByPivot(Long userId, Instant pivot) {
        GetUserNotificationByPivotResult result = notificationListService.getUserNotificationsByPivot(userId, pivot);


    }

}
