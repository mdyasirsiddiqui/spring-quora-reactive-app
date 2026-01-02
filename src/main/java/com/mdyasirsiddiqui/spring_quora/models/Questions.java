package com.mdyasirsiddiqui.spring_quora.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data@Builder@NoArgsConstructor@AllArgsConstructor
@Document(collection = "questions")
public class Questions {
    @Id
    private String id;

    @NotBlank(message = "Tillte should not be blank")
    @Size(min=10, max=100, message = "Conteny should be in between 10 andd 100 chars")
    private String title;

    private String content;
    @Builder.Default
    private Integer views=0;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
