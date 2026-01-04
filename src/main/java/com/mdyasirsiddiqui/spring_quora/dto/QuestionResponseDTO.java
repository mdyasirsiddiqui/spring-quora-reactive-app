package com.mdyasirsiddiqui.spring_quora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    private String id;

    private String title;

    private String content;

    private List<String> tags;

    private LocalDateTime createdAt;
}
