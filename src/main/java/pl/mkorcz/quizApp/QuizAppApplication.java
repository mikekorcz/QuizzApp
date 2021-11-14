package pl.mkorcz.quizApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.mkorcz.quizApp.model.Question;
import pl.mkorcz.quizApp.repository.QuestionRepository;

@SpringBootApplication
public class QuizAppApplication {

	@Autowired
	QuestionRepository questionRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner() {

		return args -> {
			Question question1 =new Question(1, "Title", "optionA", "optionB", "optionC", 2, 3);

			questionRepository.save(question1);
		};



	}
}
