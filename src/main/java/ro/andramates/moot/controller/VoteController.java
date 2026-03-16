package ro.andramates.moot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.domain.User;
import ro.andramates.moot.domain.Vote;
import ro.andramates.moot.repository.QuestionRepository;
import ro.andramates.moot.repository.UserRepository;
import ro.andramates.moot.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private final VoteService voteService;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public VoteController(
            VoteService voteService,
            UserRepository userRepository,
            QuestionRepository questionRepository
    ) {
        this.voteService = voteService;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @PostMapping
    public Vote vote(
            Authentication authentication,
            @RequestParam Integer questionId,
            @RequestParam String answer
    ) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        Question question = questionRepository.findById(questionId)
                .orElseThrow();

        return voteService.vote(user, question, answer);
    }

    @GetMapping("/results/{questionId}")
    public double getResults(@PathVariable Integer questionId) {
        return voteService.getYesPercentage(questionId);
    }
}