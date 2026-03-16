package ro.andramates.moot.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.domain.User;
import ro.andramates.moot.repository.QuestionRepository;
import ro.andramates.moot.repository.UserRepository;
import ro.andramates.moot.service.VoteService;

import java.time.LocalDate;

@Controller
public class PageController {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final VoteService voteService;

    public PageController(
            UserRepository userRepository,
            QuestionRepository questionRepository,
            VoteService voteService
    ) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String userPage(Authentication authentication) {

        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Question question = questionRepository
                .findByQuestionDate(LocalDate.now())
                .orElse(null);

        if(question == null){
            return "user"; // nu există întrebare azi
        }

        if(voteService.hasUserVoted(user,question)){
            return "redirect:/voted";
        }

        return "user";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @GetMapping("/voted")
    public String votedPage(){
        return "voted";
    }
}