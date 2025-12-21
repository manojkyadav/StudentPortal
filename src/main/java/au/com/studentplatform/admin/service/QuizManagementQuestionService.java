package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.QuizManagementQuestion;
import au.com.studentplatform.admin.repository.QuizManagementQuestionRepository;

@Service
public class QuizManagementQuestionService {
	private final QuizManagementQuestionRepository repo;

	public QuizManagementQuestionService(QuizManagementQuestionRepository repo) {
		this.repo = repo;
	}

	public List<QuizManagementQuestion> findAll() {
		return repo.findAll();
	}

	public QuizManagementQuestion findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Quiz question not found"));
	}

	public QuizManagementQuestion save(QuizManagementQuestion question) {
		return repo.save(question);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
