package com.mdyasirsiddiqui.spring_quora.service;

import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.models.QuestionElasticDocument;
import com.mdyasirsiddiqui.spring_quora.models.Questions;
import com.mdyasirsiddiqui.spring_quora.repository.QuestionDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService{

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Questions questions) {
        QuestionElasticDocument document= QuestionElasticDocument.builder()
                .id(questions.getId())
                .title((questions.getTitle()))
                .content(questions.getContent())
                .build();
        questionDocumentRepository.save(document);
    }
}
