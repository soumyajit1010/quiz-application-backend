package com.soumya.quizApp.controller;

import com.soumya.quizApp.dto.QuestionDTO;
import com.soumya.quizApp.dto.QuizResponseDTO;
import com.soumya.quizApp.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;


    @Operation(summary = "Create a quiz")
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int numQ,
            @RequestParam String title) {

        return ResponseEntity.ok(
                quizService.createQuiz(category, numQ, title)
        );
    }


    @Operation(summary = "Get quiz questions")
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(
            @PathVariable Integer id) {

        return ResponseEntity.ok(
                quizService.getQuizQuestions(id)
        );
    }


    @Operation(summary = "Submit quiz and calculate score")
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(
            @PathVariable Integer id,
            @RequestBody List<QuizResponseDTO> responses) {

        return ResponseEntity.ok(
                quizService.calculateResult(id, responses)
        );
    }
}