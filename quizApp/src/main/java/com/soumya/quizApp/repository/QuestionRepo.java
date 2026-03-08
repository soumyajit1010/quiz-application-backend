package com.soumya.quizApp.repository;

import com.soumya.quizApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    // Pagination for category
    Page<Question> findByCategory(String category, Pageable pageable);


    // Random questions for quiz creation
    @Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ",
            nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);

}