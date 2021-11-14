package pl.mkorcz.quizApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.mkorcz.quizApp.model.Question;
import pl.mkorcz.quizApp.service.QuestionService;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("/new_question")
    public String newQuestion(Model model) {
        Question question = new Question();
        model.addAttribute("question", question);
        return "new_question";
    }

    @PostMapping("/save")
    public String saveQuestion(@ModelAttribute("question")Question question) {
        questionService.saveQuestion(question);
        return "redirect:";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editQuestion(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("edit_question");
        Question question = questionService.get(id);
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        questionService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Question has been deleted");
        return "redirect:/show_questions";
    }

}
