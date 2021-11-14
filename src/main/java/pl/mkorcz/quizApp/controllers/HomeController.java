package pl.mkorcz.quizApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mkorcz.quizApp.model.Question;
import pl.mkorcz.quizApp.service.QuestionService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/show_questions")
    public String showQuestion(Model model) {
        List<Question> questionList = questionService.listAll();
        model.addAttribute("listquestion", questionList);
        return "show_questions";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) {
        if(username.equals("")) {
            redirectAttributes.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }
        return "quiz.html";
    }
}
