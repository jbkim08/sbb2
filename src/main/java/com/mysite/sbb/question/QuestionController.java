package com.mysite.sbb.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService qService;

    @RequestMapping("/question/list")
    public String questionList(Model model){
        List<Question> qList = qService.getAllQuestions();
        model.addAttribute("qList", qList);
        return "question_list";
    }
}
