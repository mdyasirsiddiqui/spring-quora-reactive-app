package com.mdyasirsiddiqui.spring_quora.consumer;

import com.mdyasirsiddiqui.spring_quora.config.KafkaConfig;
import com.mdyasirsiddiqui.spring_quora.events.ViewCountEvent;
import com.mdyasirsiddiqui.spring_quora.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaEventConsumer {

    private final QuestionRepository questionRepository;

    @KafkaListener(
            topics = KafkaConfig.TOPIC_NAME,
            groupId = "view-count-consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumeViewCountEvent(ViewCountEvent viewCountEvent)
    {
        log.info("Updating view Count "+ viewCountEvent.getTargetId());
        questionRepository.findById(viewCountEvent.getTargetId())
                .flatMap(question->{
                    question.setViews(question.getViews()+1);
                    return questionRepository.save(question);
                    })
                        .subscribe(updatedQues ->{
                            log.info("View count incremented for question: " + updatedQues.getId());
                        },
                                error->{
                            log.info("Error incrementing view count for question: " + error.getMessage());
                                });

    }
}
