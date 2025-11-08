package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.QuestionOption;
import com.example.studentplatform.repository.OptionRepository;

@Service
public class OptionService {
	private final OptionRepository repo;

	public OptionService(OptionRepository repo) {
		this.repo = repo;
	}

	public List<QuestionOption> findAll() {
		return repo.findAll();
	}

	public QuestionOption findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Option not found"));
	}

	public QuestionOption save(QuestionOption option) {
		return repo.save(option);
	}
	public List<QuestionOption> saveAll(List<QuestionOption> options ) {
		return repo.saveAll(options);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	List<QuestionOption> findByQuestionId(Integer questionId){
		return repo.findByQuestionId(questionId);
	}
}
