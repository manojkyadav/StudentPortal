package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.QuizManagementResult;
import au.com.studentplatform.admin.repository.QuizManagementResultRepository;

@Service
public class QuizManagementResultService {
	private final QuizManagementResultRepository repo;

	public QuizManagementResultService(QuizManagementResultRepository repo) {
		this.repo = repo;
	}

	public List<QuizManagementResult> findAll() {
		return repo.findAll();
	}

	public QuizManagementResult findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Quiz result not found"));
	}

	public QuizManagementResult save(QuizManagementResult result) {
		return repo.save(result);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
