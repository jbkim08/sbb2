package com.mysite.sbb.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService qService;

    @RequestMapping("/list")
    public String questionList(Model model){
        List<Question> qList = qService.getAllQuestions();
        model.addAttribute("qList", qList);
        return "question_list";
    }

    //url 주소에 /{변수} => PathVariable 주소변수
    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id){
        Question q = qService.getQuestionById(id);
        model.addAttribute("question", q);
        return "question_detail";
    }
}
