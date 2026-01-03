package com.mdyasirsiddiqui.spring_quora.service;

import com.mdyasirsiddiqui.spring_quora.adapter.QuestionAdapter;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionResponseDTO;
import com.mdyasirsiddiqui.spring_quora.models.Questions;
import com.mdyasirsiddiqui.spring_quora.repository.QuestionRepository;
import com.mdyasirsiddiqui.spring_quora.utils.CursorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Questions questions = QuestionAdapter.dtoTOQuestionModel(questionRequestDTO);
//       Mono<Questions> saved =questionRepository.save(questions);
//       Mono<QuestionResponseDTO> responseDTO= saved.map(QuestionAdapter::modelToResponseDTO);
//       return responseDTO;
        return questionRepository.save(questions)
                .map(QuestionAdapter::modelToResponseDTO)
                .doOnSubscribe(response -> System.out.println("Question Created successfully" + response))
                .doOnError(error -> System.out.println("Error creating question" + error));

    }

    @Override
    public Mono<QuestionResponseDTO> findQuestionById(String id) {
        return questionRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Question not found")))
                .map(QuestionAdapter::modelToResponseDTO)
                .doOnSuccess(response -> log.info("Question retrieved successfully:{}", response))
                .doOnError(error -> log.error("error in fetching question:{}", error));
    }

    @Override
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm, int offset, int page) { //offset->page, page->size or number of result per page
        return questionRepository.findByTitleOrContentContainingIgnoreCase(searchTerm, PageRequest.of(offset, page))
                .switchIfEmpty(Flux.error(new RuntimeException("Question not found")))
                .map(QuestionAdapter::modelToResponseDTO)
                .doOnNext(response -> log.info("Question found sucessfully:{} ", response))
                .doOnError(error -> log.info("error in fetching question:{}", error));
    }

    @Override
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size) {
        Pageable pageable = PageRequest.of(0, size);
        if (!CursorUtils.isCursorValid(cursor)) {
            return questionRepository.findTop10ByOrderByCreatedAtAsc()
                    .take(size)
                    .map(QuestionAdapter::modelToResponseDTO)
                    .doOnNext(response -> log.info("Question found sucessfully:{} ", response))
                    .doOnError(error -> log.info("error in fetching question:{}", error));
        } else {
            LocalDateTime cursorTimeStamp = CursorUtils.parseCursor(cursor);
            return questionRepository.findByCreatedAtGreaterThanOrderByCreatedAtAsc(cursorTimeStamp, pageable)
                    .map(QuestionAdapter::modelToResponseDTO)
                    .doOnNext(response -> log.info("Question found sucessfully:{} ", response))
                    .doOnError(error -> log.info("error in fetching question:{}", error));
        }
    }
}


