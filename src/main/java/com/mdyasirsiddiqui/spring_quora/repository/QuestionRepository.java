
package com.mdyasirsiddiqui.spring_quora.repository;

import com.mdyasirsiddiqui.spring_quora.models.Questions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Questions,String> {

    @Query("{ '$or': [ { 'title': { $regex: ?0, $options: 'i'} }, { 'content' : { $regex: ?0, $options: 'i' } } ] }")
    public Flux<Questions> findByTitleOrContentContainingIgnoreCase(String searchTerm, Pageable pageable);

    public Flux<Questions> findByCreatedAtGreaterThanOrderByCreatedAtAsc(LocalDateTime cursor, Pageable pageable);
    public Flux<Questions> findTop10ByOrderByCreatedAtAsc();
    public Flux<Questions> findByTagsContainingIgnoreCase(String tags,Pageable pageable);
}

