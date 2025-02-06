package kr.co.kimga.service.converter;

import kr.co.kimga.client.PostClient;
import kr.co.kimga.client.UserClient;
import kr.co.kimga.domain.CommentNotification;
import kr.co.kimga.domain.Post;
import kr.co.kimga.domain.User;
import kr.co.kimga.service.dto.ConvertedCommentNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentUserNotificationConvertor {

    private final UserClient userClient;
    private final PostClient postClient;

    public ConvertedCommentNotification convert(CommentNotification notification) {
        User user = userClient.getUser(notification.getUserId());
        Post post = postClient.getPost(notification.getPostId());

        return new ConvertedCommentNotification(
                notification.getId(),
                notification.getType(),
                notification.getOccurredAt(),
                notification.getLastUpdatedAt(),
                user.getName(),
                user.getProfileImageUrl(),
                notification.getComment(),
                post.getImageUrl()
        );
    }
}
