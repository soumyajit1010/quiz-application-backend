package com.soumya.quizApp.controller;

import com.soumya.quizApp.entity.Question;
import com.soumya.quizApp.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @Operation(summary = "Get all questions with pagination")
    @GetMapping("/allQuestions")
    public ResponseEntity<Page<Question>> getAllQuestions(
            @RequestParam int page,
            @RequestParam int size){

        return ResponseEntity.ok(
                questionService.getAllQuestions(page, size)
        );
    }


    @Operation(summary = "Get questions by category with pagination")
    @GetMapping("/category/{category}")
    public ResponseEntity<Page<Question>> getQuestionsByCategory(
            @PathVariable String category,
            @RequestParam int page,
            @RequestParam int size){

        return ResponseEntity.ok(
                questionService.getQuestionsByCategory(category, page, size)
        );
    }


    @Operation(summary = "Add a new question")
    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){

        return ResponseEntity.ok(
                questionService.addQuestion(question)
        );
    }

}