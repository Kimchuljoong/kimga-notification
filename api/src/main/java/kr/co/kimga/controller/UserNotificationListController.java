package kr.co.kimga.controller;

import kr.co.kimga.response.UserNotificationListResponse;
import kr.co.kimga.response.UserNotificationResponse;
import kr.co.kimga.service.GetUserNotificationsService;
import kr.co.kimga.service.dto.GetUserNotificationsResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user-notification")
public class UserNotificationListController implements UserNotificationListControllerSpec {

    private final GetUserNotificationsService getUserNotificationsService;

    @Override
    @GetMapping("/{userId}")
    public UserNotificationListResponse getNotifications(
            @PathVariable("userId") Long userId,
            @RequestParam(value = "pivot", required = false) Instant pivot
    ) {
        return UserNotificationListResponse.of(
                getUserNotificationsService.getUserNotificationByPivot(userId, pivot)
        );
    }
}
