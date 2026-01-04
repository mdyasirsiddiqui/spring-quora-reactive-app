package com.mdyasirsiddiqui.spring_quora.adapter;

import com.mdyasirsiddiqui.spring_quora.dto.QuestionRequestDTO;
import com.mdyasirsiddiqui.spring_quora.dto.QuestionResponseDTO;
import com.mdyasirsiddiqui.spring_quora.models.Questions;

import java.time.LocalDateTime;
public class QuestionAdapter {
    public static QuestionResponseDTO modelToResponseDTO(Questions questions)
    {
        return QuestionResponseDTO.builder()
                .id(questions.getId())
                .title(questions.getTitle())
                .tags((questions.getTags()))
                .content(questions.getContent())
                .createdAt(questions.getCreatedAt())
                .build();
    }
    public static Questions dtoTOQuestionModel(QuestionRequestDTO dto)
    {
        return Questions.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
