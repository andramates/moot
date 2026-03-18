package ro.andramates.moot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.service.QuestionService;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/today")
    public Question getTodayQuestion() {

        return questionService.getTodayQuestion().orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Question createQuestion(@RequestBody Question question) {

        return questionService.createQuestion(
                question.getText(),
                question.getQuestionDate()
        );
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.findAll();
    }
}