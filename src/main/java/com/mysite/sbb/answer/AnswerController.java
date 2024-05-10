package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private QuestionService qService;
    @Autowired
    private AnswerService aService;

    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable int id, Model model,
                               @Valid AnswerForm answerForm, BindingResult result) {
        Question question = qService.getQuestionById(id);
        if(result.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail"; //질문상세페이지로 돌아감
        }
        //답변저장하기
        aService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%d", id);
    }
}
