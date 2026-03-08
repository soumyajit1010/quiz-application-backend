package com.soumya.quizApp.service;

import com.soumya.quizApp.dto.QuestionDTO;
import com.soumya.quizApp.dto.QuizResponseDTO;
import com.soumya.quizApp.entity.Question;
import com.soumya.quizApp.entity.Quiz;
import com.soumya.quizApp.exception.ResourceNotFoundException;
import com.soumya.quizApp.mapper.QuestionMapper;
import com.soumya.quizApp.repository.QuestionRepo;
import com.soumya.quizApp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuestionRepo questionRepo;


    // Create quiz
    public String createQuiz(String category, int numQ, String title) {

        List<Question> questions =
                questionRepo.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepo.save(quiz);

        return "Quiz created successfully";
    }


    // Get quiz questions
    public List<QuestionDTO> getQuizQuestions(Integer id) {

        Quiz quiz = quizRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found with id: " + id));

        List<Question> questions = quiz.getQuestions();

        List<QuestionDTO> result = new ArrayList<>();

        for (Question q : questions) {
            result.add(QuestionMapper.toDTO(q));
        }

        return result;
    }


    // Calculate quiz result
    public Integer calculateResult(Integer id, List<QuizResponseDTO> responses) {

        Quiz quiz = quizRepo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found with id: " + id));

        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;

        for (QuizResponseDTO response : responses) {

            if (response.getResponse()
                    .equals(questions.get(i).getRightAnswer())) {

                right++;
            }

            i++;
        }

        return right;
    }
}