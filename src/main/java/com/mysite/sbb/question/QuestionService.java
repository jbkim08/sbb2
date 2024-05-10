package com.mysite.sbb.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository qRepo;

    public List<Question> getAllQuestions() {
        return qRepo.findAll();
    }
}
