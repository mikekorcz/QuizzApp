package pl.mkorcz.quizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mkorcz.quizApp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
