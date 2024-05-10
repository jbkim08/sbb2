package com.mysite.sbb.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository qRepo;

    public List<Question> getAllQuestions() {
        return qRepo.findAll();
    }

    public Question getQuestionById(int id) {
        Optional<Question> q = qRepo.findById(id);
        if (q.isPresent()) {
            return q.get();
        } else {
            //id에 해당하는 질문을 못찾을 경우 에러를 발생하며 메세지 표시 , 404 상태코드
            throw new DataNotFoundException("question not found");
        }
    }

    public void createQuestion(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        qRepo.save(q);
    }
}
