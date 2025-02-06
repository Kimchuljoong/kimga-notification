package kr.co.kimga.service.converter;

import kr.co.kimga.client.PostClient;
import kr.co.kimga.client.UserClient;
import kr.co.kimga.domain.CommentNotification;
import kr.co.kimga.domain.LikeNotification;
import kr.co.kimga.domain.Post;
import kr.co.kimga.domain.User;
import kr.co.kimga.service.dto.ConvertedCommentNotification;
import kr.co.kimga.service.dto.ConvertedLikeNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedLikeNotification convert(LikeNotification notification) {
        User user = userClient.getUser(notification.getLikerIds().getLast());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedLikeNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getLikerIds().size(),
                post.getImageUrl()
        );
    }
}
