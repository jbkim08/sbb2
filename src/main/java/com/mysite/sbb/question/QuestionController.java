package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService qService;

    @RequestMapping("/list")
    public String questionList(Model model, @RequestParam(value = "page", defaultValue = "0") int page){
        Page<Question> paging = qService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    //url 주소에 /{변수} => PathVariable 주소변수
    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") int id,
                         AnswerForm answerForm){
        Question q = qService.getQuestionById(id);
        model.addAttribute("question", q);
        return "question_detail";
    }

    @GetMapping("/create")
    public String create(QuestionForm questionForm){
        return "question_form";
    }

    @PostMapping("/create")
    public String create(@Valid QuestionForm questionForm,
                         BindingResult result){
        if(result.hasErrors()){
            return "question_form"; //되돌아감
        }
        //질문 저장하기
        qService.createQuestion(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
