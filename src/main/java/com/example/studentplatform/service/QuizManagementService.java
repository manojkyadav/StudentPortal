package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.QuizManagement;
import com.example.studentplatform.repository.QuizManagementRepository;

@Service
public class QuizManagementService {
	private final QuizManagementRepository repo;

	public QuizManagementService(QuizManagementRepository repo) {
		this.repo = repo;
	}

	public List<QuizManagement> findAll() {
		return repo.findAll();
	}

	public QuizManagement findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
	}

	public QuizManagement save(QuizManagement quiz) {
		return repo.save(quiz);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
