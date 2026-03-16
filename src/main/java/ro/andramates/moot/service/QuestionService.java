package ro.andramates.moot.service;

import org.springframework.stereotype.Service;
import ro.andramates.moot.domain.Question;
import ro.andramates.moot.repository.QuestionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> getTodayQuestion() {
        return questionRepository.findByQuestionDate(LocalDate.now());
    }

    public Question createQuestion(String text, LocalDate date) {

        Question question = Question.builder()
                .text(text)
                .questionDate(date)
                .build();

        return questionRepository.save(question);
    }

    public void deactivateQuestion(Integer id) {

        Question q = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));


        questionRepository.save(q);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

}