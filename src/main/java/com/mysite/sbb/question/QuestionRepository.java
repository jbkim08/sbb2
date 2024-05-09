package com.mysite.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    //Question findByContent(String content);
    Question findBySubjectAndContent(String subject, String content);
    //질문제목에 해당하는 문자열 있을경우 모두 가져옴
    List<Question> findBySubjectLike(String subject);
}
