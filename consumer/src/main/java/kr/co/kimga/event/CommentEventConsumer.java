package kr.co.kimga.event;

import kr.co.kimga.task.CommentAddTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentEventConsumer {

    private final CommentAddTask commentAddTask;

    @Bean("comment")
    public Consumer<CommentEvent> comment() {
        return event -> {
            if (event.getType() == CommentEventType.ADD) {
                commentAddTask.processEvent(event);
            } else if (event.getType() == CommentEventType.REMOVE) {
                commentAddTask.processEvent(event);
            }
        };
    }
}
