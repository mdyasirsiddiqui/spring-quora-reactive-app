
package com.mdyasirsiddiqui.spring_quora.repository;

import com.mdyasirsiddiqui.spring_quora.models.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Questions,String> {
}
