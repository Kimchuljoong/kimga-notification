package kr.co.kimga.controller;

import io.swagger.v3.oas.annotations.Parameter;
import kr.co.kimga.response.CheckNewNotificationResponse;
import kr.co.kimga.service.CheckNewNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CheckNewNotificationController implements CheckNewNotificationControllerSpec{

    private final CheckNewNotificationService checkNewNotificationService;

    @Override
    @GetMapping("/{userId}/new")
    public CheckNewNotificationResponse checkNew(
            @PathVariable("userId") Long userId
    ) {
        boolean hasNew = checkNewNotificationService.checkNewNotification(userId);
        return new CheckNewNotificationResponse(hasNew);
    }
}
