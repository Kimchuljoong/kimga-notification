package kr.co.kimga;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommentClient {

    private final Map<Long, Comment> comments = new HashMap<>();

    public CommentClient() {
        comments.put(1L, new Comment(1L, "content1", Instant.now()));
        comments.put(2L, new Comment(2L, "content2", Instant.now()));
        comments.put(3L, new Comment(3L, "content3", Instant.now()));
    }

    public Comment getComment(long id) {
        return comments.get(id);
    }
}
