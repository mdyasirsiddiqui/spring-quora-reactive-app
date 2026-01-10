package com.mdyasirsiddiqui.spring_quora.controller;

import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionResponseDTO;
import com.mdyasirsiddiqui.spring_quora.service.IQuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final IQuestionService service;

    @PostMapping
    public Mono<QuestionResponseDTO> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {
        return service.createQuestion(questionRequestDTO)
                .doOnSuccess(response -> System.out.println("Question created successfully: " + response))
                .doOnError(error -> System.out.println("Error creating question: " + error));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<QuestionResponseDTO>> getQuestionById(@PathVariable String id) {
        log.info("fetching qustionfor given id ", id);
        return service.findQuestionById(id)
                .map(response -> {
                    log.info("Question fetched successfully: {}", response);
                    return ResponseEntity.ok(response);
                })
                .switchIfEmpty(Mono.defer(() -> {
                    log.warn("No question found for id: {}", id);
                    return Mono.just(ResponseEntity.notFound().build());
                }))
                .doOnError(error -> log.error("Unable to fetch question for id: {}", id, error));
    }

    @GetMapping("/search")
    public Flux<QuestionResponseDTO> searchQuestion(
            @RequestParam String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return service.searchQuestions(query,page,size);
    }

    @GetMapping("/all")
    public Flux<QuestionResponseDTO> getAllQuestions(
            @RequestParam(required = false) String cursor,
            @RequestParam int size)
    {
        return service.getAllQuestions(cursor, size);//try to pagination response //tag based api
    }

    @GetMapping("/tag")
    public Flux<QuestionResponseDTO> getAllQuestionsByTag(@RequestParam String tags,
                                                          @RequestParam(defaultValue = "10") int size)
    {
            return service.getQuestionsByTag(tags,size);
    }

}
