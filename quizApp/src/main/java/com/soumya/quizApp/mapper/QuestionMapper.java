package com.soumya.quizApp.mapper;

import com.soumya.quizApp.dto.QuestionDTO;
import com.soumya.quizApp.entity.Question;

public class QuestionMapper {
    public static QuestionDTO toDTO(Question question) {
        return new QuestionDTO(
                question.getId(),
                question.getQuestionTitle(),
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
        );
    }
}
