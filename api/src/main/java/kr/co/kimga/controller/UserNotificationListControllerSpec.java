package kr.co.kimga.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.kimga.response.UserNotificationListResponse;

import java.time.Instant;

@Tag(name = "사용자 알림센터 API")
public interface UserNotificationListControllerSpec {

    @Operation(summary = "사용자 알림 조회")
    UserNotificationListResponse getNotifications(
            @Parameter(example = "1") Long userId,
            @Parameter(example = "2025-02-08T10:34:12.000Z") Instant pivot
    );
}
