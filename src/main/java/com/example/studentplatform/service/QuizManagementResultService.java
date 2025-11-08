package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.QuizManagementResult;
import com.example.studentplatform.repository.QuizManagementResultRepository;

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
