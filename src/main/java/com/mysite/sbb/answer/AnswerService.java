package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository aRepo;

    public void create(Question question, String content) {
       //답변의 부모가 질문이기 때문에 질문필요
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question); //질문 입력
        aRepo.save(answer);
    }
}
