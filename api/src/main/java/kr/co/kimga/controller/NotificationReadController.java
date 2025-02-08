package kr.co.kimga.controller;


import kr.co.kimga.response.SetLastReadAtResponse;
import kr.co.kimga.service.LastReadAtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-notifications")
public class NotificationReadController implements NotificationControllerSpec{

    private final LastReadAtService lastReadAtService;

    @Override
    @PutMapping("/{userId}")
    public SetLastReadAtResponse setLastReadAt(
            @PathVariable Long userId
    ) {
        Instant lastReadAt = lastReadAtService.setLastReadAt(userId);
        return new SetLastReadAtResponse(lastReadAt);
    }
}
