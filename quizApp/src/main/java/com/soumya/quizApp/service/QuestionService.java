package com.soumya.quizApp.service;

import com.soumya.quizApp.entity.Question;
import com.soumya.quizApp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepo questionRepo;


    // Get all questions with pagination
    public Page<Question> getAllQuestions(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return questionRepo.findAll(pageable);
    }


    // Get questions by category with pagination
    public Page<Question> getQuestionsByCategory(String category, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return (Page<Question>) questionRepo.findByCategory(category, pageable);
    }


    // Add new question
    public Question addQuestion(Question question) {

        return questionRepo.save(question);
    }
}