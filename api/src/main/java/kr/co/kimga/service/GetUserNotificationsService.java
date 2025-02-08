package kr.co.kimga.service;

import kr.co.kimga.domain.CommentNotification;
import kr.co.kimga.domain.FollowNotification;
import kr.co.kimga.domain.LikeNotification;
import kr.co.kimga.service.converter.CommentUserNotificationConvertor;
import kr.co.kimga.service.converter.FollowUserNotificationConvertor;
import kr.co.kimga.service.converter.LikeUserNotificationConvertor;
import kr.co.kimga.service.dto.ConvertedNotification;
import kr.co.kimga.service.dto.GetUserNotificationByPivotResult;
import kr.co.kimga.service.dto.GetUserNotificationsResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetUserNotificationsService {

    private final NotificationListService notificationListService;
    private final CommentUserNotificationConvertor commentConvertor;
    private final LikeUserNotificationConvertor likeConvertor;
    private final FollowUserNotificationConvertor followConvertor;

    public GetUserNotificationsResult getUserNotificationByPivot(Long userId, Instant pivot) {
        GetUserNotificationByPivotResult result = notificationListService.getUserNotificationsByPivot(userId, pivot);

        List<ConvertedNotification> convertedNotifications = result.notifications().stream()
                .map(notification -> switch (notification.getType()) {
                    case COMMENT -> commentConvertor.convert((CommentNotification) notification);
                    case LIKE -> likeConvertor.convert((LikeNotification) notification);
                    case FOLLOW -> followConvertor.convert((FollowNotification) notification);
                }).toList();

        return new GetUserNotificationsResult(
                convertedNotifications,
                result.hasNext()
        );
    }

}
