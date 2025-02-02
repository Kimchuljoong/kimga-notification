package kr.co.kimga.event;

import kr.co.kimga.task.FollowAddTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowEventConsumer {

    private final FollowAddTask followAddTask;

    @Bean("follow")
    public Consumer<FollowEvent> follow() {
        return event -> {
            if (event.getType() == FollowEventType.ADD) {
                followAddTask.processEvent(event);
            }
        };
    }
}
