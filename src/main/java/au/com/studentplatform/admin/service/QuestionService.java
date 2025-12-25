package au.com.studentplatform.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.TestMode;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.repository.QuestionRepository;
import au.com.studentplatform.admin.repository.TopicRepository;
import jakarta.transaction.Transactional;

@Service
public class QuestionService {
	private QuestionRepository questionRepository;
	private TopicRepository topicRepository;

	public QuestionService(QuestionRepository questionRepository, TopicRepository topicRepository) {
		this.questionRepository = questionRepository;
		this.topicRepository = topicRepository;
	}

	public List<Question> findAll() {
		return questionRepository.findAll();
	}

	public Question findById(Integer id) {
		return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
	}

	public Question save(Question question) {
		// IMPORTANT: set parent for children
		if (question.getOptions() != null) {
			question.getOptions().removeIf(o -> o.getText() == null || o.getText().isBlank());

			question.getOptions().forEach(o -> o.setQuestion(question));
		}

		return questionRepository.save(question);
	}

	public void deleteById(Integer id) {
		questionRepository.deleteById(id);
	}

	public List<Question> getQuestions(Integer topicId, String mode) {
		if (mode.equals(TestMode.EXAM)) {
			return questionRepository.findRandomQuestions(topicId, PageRequest.of(0, 20));
		} else {
			// return questionRepository.findByTopic(topicId);
			Topic topic = topicRepository.findById(topicId).orElseThrow();
			List<Question> questions = questionRepository.findByTopic(topic);

			return questions;
		}
	}
	
	@Transactional
	public Question getQuestionWithOptions(Integer id) {
		Optional<Question>  questions = questionRepository.findByIdWithOptions(id);
		if (questions.get() == null) {
			Question question = new Question();
			return question;
		}
		
		return questions.get();
	    //return questionRepository.findByIdWithOptions(id).orElseThrow();
	}

}
