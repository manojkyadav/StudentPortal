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

import com.example.studentplatform.model.QuizManagement;
import com.example.studentplatform.service.QuizManagementService;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin
public class QuizManagementController {
	private final QuizManagementService service;

	public QuizManagementController(QuizManagementService service) {
		this.service = service;
	}

	@GetMapping
	public List<QuizManagement> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public QuizManagement getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public QuizManagement create(@RequestBody QuizManagement quiz) {
		return service.save(quiz);
	}

	@PutMapping("/{id}")
	public QuizManagement update(@PathVariable Integer id, @RequestBody QuizManagement quiz) {
		quiz.setId(id);
		return service.save(quiz);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
