package pl.mkorcz.quizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkorcz.quizApp.model.Result;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}
