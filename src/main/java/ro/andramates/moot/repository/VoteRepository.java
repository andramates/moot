package ro.andramates.moot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.domain.User;
import ro.andramates.moot.domain.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByUserAndQuestion(User user, Question question);

    long countByQuestionIdAndAnswer(Integer questionId, String answer);

    boolean existsByUserAndQuestion(User user, Question question);

}