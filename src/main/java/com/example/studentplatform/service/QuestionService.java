package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Question;
import com.example.studentplatform.repository.QuestionRepository;

@Service
public class QuestionService {
	private final QuestionRepository repo;

	public QuestionService(QuestionRepository repo) {
		this.repo = repo;
	}

	public List<Question> findAll() {
		return repo.findAll();
	}

	public Question findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Question not found"));
	}

	public Question save(Question question) {
		return repo.save(question);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
