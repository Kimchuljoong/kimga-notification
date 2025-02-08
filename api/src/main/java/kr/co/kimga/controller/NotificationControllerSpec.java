package kr.co.kimga.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.kimga.response.SetLastReadAtResponse;

@Tag(name = "사용자 알림센터 API")
public interface NotificationControllerSpec {

    @Operation(summary = "사용자가 알림 목록을 읽은 시간 기록")
    SetLastReadAtResponse setLastReadAt(
            @Parameter(example = "1") Long userId
    );

}
