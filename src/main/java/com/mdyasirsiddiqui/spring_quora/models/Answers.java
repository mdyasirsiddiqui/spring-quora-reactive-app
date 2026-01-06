package com.mdyasirsiddiqui.spring_quora.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "answers")
public class Answers {
    @Id
    private String id;

    @NotNull(message = "Content cannot be null")
    @Size(min = 10, max = 10000, message = "Content must be 10 nad 10000")

    private String Content;
    @Indexed
    private String questionId;

    @CreatedDate
    @Indexed
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
