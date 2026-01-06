package com.mdyasirsiddiqui.spring_quora.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="likes")
public class Like {
    @Id
    private String id;

    private String targetId;
    // we will create enum for target type so that it will hlp us in future if we have add feature lke stories.
    private String targetType;//QUESTIONS, ANSWERS

    private boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
