package com.mdyasirsiddiqui.spring_quora.service;

import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionResponseDTO;
import com.mdyasirsiddiqui.spring_quora.models.QuestionElasticDocument;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IQuestionService  {
    public Mono<QuestionResponseDTO> createQuestion(QuestionRequestDTO questionRequestDTO);
    public  Mono<QuestionResponseDTO> findQuestionById(String id);
    public Flux<QuestionResponseDTO> searchQuestions(String searchTerm,int offset,int page);
    public Flux<QuestionResponseDTO> getAllQuestions(String cursor, int size);//offset->page, page->size
    public  Flux<QuestionResponseDTO> getQuestionsByTag(String tag, int size);
    public List<QuestionElasticDocument> searchQuestionsByElasticsearch(String query);
}
