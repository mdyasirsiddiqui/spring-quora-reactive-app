package com.mdyasirsiddiqui.spring_quora.service;

import com.mdyasirsiddiqui.spring_quora.adapter.QuestionAdapter;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionResponseDTO;
import com.mdyasirsiddiqui.spring_quora.models.Questions;
import com.mdyasirsiddiqui.spring_quora.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class QuestionService implements IQuestionService{
    private final QuestionRepository questionRepository;
    @Override
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO) {
        Questions questions=QuestionAdapter.dtoTOQuestionModel(questionRequestDTO);
//       Mono<Questions> saved =questionRepository.save(questions);
//       Mono<QuestionResponseDTO> responseDTO= saved.map(QuestionAdapter::modelToResponseDTO);
//       return responseDTO;
        return questionRepository.save(questions)
                .map(QuestionAdapter::modelToResponseDTO)
                .doOnSubscribe(response -> System.out.println("Question Created successfully"+ response))
                .doOnError(error -> System.out.println("Error creating question"+ error));

    }
}
