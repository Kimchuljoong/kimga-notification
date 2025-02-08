package kr.co.kimga.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.kimga.domain.NotificationType;
import kr.co.kimga.service.dto.ConvertedCommentNotification;
import kr.co.kimga.service.dto.ConvertedFollowNotification;
import kr.co.kimga.service.dto.ConvertedLikeNotification;
import kr.co.kimga.service.dto.ConvertedNotification;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
@Schema(description = "유저 알림 응답")
@JsonSubTypes({
        @JsonSubTypes.Type(value = CommentUserNotificationResponse.class),
        @JsonSubTypes.Type(value = LikeUserNotificationResponse.class),
        @JsonSubTypes.Type(value = FollowUserNotificationResponse.class),
})
public abstract class UserNotificationResponse {

    @Schema(description = "알림 ID")
    private String id;

    @Schema(description = "알림 타입")
    private NotificationType type;

    @Schema(description = "알림 이벤트 발생 시간")
    private Instant occurredAt;

    public static UserNotificationResponse of(ConvertedNotification notification) {
        switch (notification.getType()) {
            case COMMENT -> { return CommentUserNotificationResponse.of((ConvertedCommentNotification) notification);}
            case LIKE -> { return LikeUserNotificationResponse.of((ConvertedLikeNotification) notification);}
            case FOLLOW -> { return FollowUserNotificationResponse.of((ConvertedFollowNotification) notification);}
            default -> throw new IllegalArgumentException("unsupported notification type: " + notification.getType());
        }
    }

}
