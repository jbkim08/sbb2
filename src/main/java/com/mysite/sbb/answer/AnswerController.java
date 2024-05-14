package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    private QuestionService qService;
    @Autowired
    private AnswerService aService;
    @Autowired
    private UserService uService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(@PathVariable int id, Model model,
                               @Valid AnswerForm answerForm, BindingResult result,
                               Principal principal) {
        Question question = qService.getQuestionById(id);
        SiteUser siteUser = uService.getUser(principal.getName());
        if(result.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail"; //질문상세페이지로 돌아감
        }
        //답변저장하기
        aService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%d", id);
    }
}
