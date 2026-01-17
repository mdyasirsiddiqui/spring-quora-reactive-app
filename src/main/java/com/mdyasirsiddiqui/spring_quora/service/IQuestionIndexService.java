package com.mdyasirsiddiqui.spring_quora.service;

import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.models.Questions;
import com.mdyasirsiddiqui.spring_quora.repository.QuestionDocumentRepository;

public interface IQuestionIndexService {

    void createQuestionIndex(Questions questions);
}
