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
import pl.mkorcz.quizApp.model.QuestionForm;
import pl.mkorcz.quizApp.model.Result;
import pl.mkorcz.quizApp.service.QuestionService;
import pl.mkorcz.quizApp.service.QuizService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;
    @Autowired
    QuestionService questionService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }

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
        if (username.equals("")) {
            redirectAttributes.addFlashAttribute("warning", "You must enter your name");
            return "redirect:/";
        }

        submitted = false;
        result.setUsername(username);

        QuestionForm questionForm = quizService.getQuestionForm();
        model.addAttribute("questionForm", questionForm);
        return "quiz.html";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute QuestionForm questionForm, Model model) {
        if (!submitted) {
            result.setTotalCorrect(quizService.getResult(questionForm));
        }
        return "/result.html";
    }
}
