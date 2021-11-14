package pl.mkorcz.quizApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.mkorcz.quizApp.model.Question;
import pl.mkorcz.quizApp.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository repo;

    public List<Question> listAll() {
        return repo.findAll();
    }

    public void saveQuestion(Question question) {
        repo.save(question);
    }

    public Question get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
