package com.mdyasirsiddiqui.spring_quora.repository;

import com.mdyasirsiddiqui.spring_quora.models.QuestionElasticDocument;
import com.mdyasirsiddiqui.spring_quora.models.Questions;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDocumentRepository extends ElasticsearchRepository<QuestionElasticDocument,String> {
    public List<QuestionElasticDocument> findByTitleContainingOrContentContaining(String title, String  content);
}
