package ro.andramates.moot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.andramates.moot.domain.Question;

import java.time.LocalDate;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Optional<Question> findByQuestionDate(LocalDate questionDate);

}