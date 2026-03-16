package ro.andramates.moot.service;

import org.springframework.stereotype.Service;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.domain.User;
import ro.andramates.moot.domain.Vote;
import ro.andramates.moot.repository.VoteRepository;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote vote(User user, Question question, String answer) {

        if (voteRepository.findByUserAndQuestion(user, question).isPresent()) {
            throw new RuntimeException("User already voted");
        }

        Vote vote = Vote.builder()
                .user(user)
                .question(question)
                .answer(answer)
                .build();

        return voteRepository.save(vote);
    }

    public double getYesPercentage(Integer questionId) {

        long yes = voteRepository.countByQuestionIdAndAnswer(questionId, "YES");
        long no = voteRepository.countByQuestionIdAndAnswer(questionId, "NO");

        long total = yes + no;

        if (total == 0) return 0;

        return (yes * 100.0) / total;
    }

    public boolean hasUserVoted(User user, Question question) {
        return voteRepository.existsByUserAndQuestion(user, question);
    }
}