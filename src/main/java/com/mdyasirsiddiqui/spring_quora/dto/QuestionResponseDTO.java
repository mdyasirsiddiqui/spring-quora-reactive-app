package com.mdyasirsiddiqui.spring_quora.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionResponseDTO {

    private String id;

    private String title;

    private String content;

    private LocalDateTime createdAt;
}
