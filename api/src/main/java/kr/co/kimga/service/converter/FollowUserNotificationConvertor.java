package kr.co.kimga.service.converter;

import kr.co.kimga.client.PostClient;
import kr.co.kimga.client.UserClient;
import kr.co.kimga.domain.CommentNotification;
import kr.co.kimga.domain.FollowNotification;
import kr.co.kimga.domain.Post;
import kr.co.kimga.domain.User;
import kr.co.kimga.service.dto.ConvertedCommentNotification;
import kr.co.kimga.service.dto.ConvertedFollowNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedFollowNotification convert(FollowNotification notification) {
        User user = userClient.getUser(notification.getFollowerId());
        boolean isFollowing = userClient.getIsFollowing(notification.getUserId(), notification.getFollowerId());

        return new ConvertedFollowNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                isFollowing
        );
    }
}
