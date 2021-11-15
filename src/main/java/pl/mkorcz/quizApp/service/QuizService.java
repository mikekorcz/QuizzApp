package pl.mkorcz.quizApp.service;

import net.bytebuddy.TypeCache;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.mkorcz.quizApp.model.Question;
import pl.mkorcz.quizApp.model.QuestionForm;
import pl.mkorcz.quizApp.model.Result;
import pl.mkorcz.quizApp.repository.QuestionRepository;
import pl.mkorcz.quizApp.repository.ResultRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired
    Question question;
    @Autowired
    QuestionForm questionForm;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    Result result;
    @Autowired
    ResultRepository resultRepository;

    public QuestionForm getQuestionForm() {
        List<Question> allQuestions = questionRepository.findAll();
        List<Question> questionList = new ArrayList<Question>();

        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int rand = random.nextInt(allQuestions.size());
            questionList.add(allQuestions.get(rand));
            allQuestions.remove(rand);
        }

        questionForm.setQuestions(questionList);

        return questionForm;
    }

    public int getResult(QuestionForm questionForm) {
        int correct = 0;

        for (Question question : questionForm.getQuestions()) {
            if (question.getAnswer() == question.getChoice())
                correct++;
        }
        return correct;
    }

    public void saveScore(Result result) {
        Result saveResult = new Result();
        saveResult.setUsername(result.getUsername());
        saveResult.setTotalCorrect(result.getTotalCorrect());
        resultRepository.save(saveResult);
    }

    public List<Result> getTopScore() {
        List<Result> sList = resultRepository.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        return sList;
    }
}
