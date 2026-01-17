package com.mdyasirsiddiqui.spring_quora.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

//@Document(indexName= "questions")
@Document(indexName = "questions")  ///  this anotation is from data.elasticSearch.ann.document
public class QuestionElasticDocument {

    @Id
    private String id;

    private String content;

    private String title;
}
