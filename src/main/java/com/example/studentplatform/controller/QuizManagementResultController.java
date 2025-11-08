package com.example.studentplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentplatform.model.QuizManagementResult;
import com.example.studentplatform.service.QuizManagementResultService;

@RestController
@RequestMapping("/api/quiz-results")
@CrossOrigin
public class QuizManagementResultController {
	private final QuizManagementResultService service;

	public QuizManagementResultController(QuizManagementResultService service) {
		this.service = service;
	}

	@GetMapping
	public List<QuizManagementResult> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public QuizManagementResult getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public QuizManagementResult create(@RequestBody QuizManagementResult result) {
		return service.save(result);
	}

	@PutMapping("/{id}")
	public QuizManagementResult update(@PathVariable Integer id, @RequestBody QuizManagementResult result) {
		result.setId(id);
		return service.save(result);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
