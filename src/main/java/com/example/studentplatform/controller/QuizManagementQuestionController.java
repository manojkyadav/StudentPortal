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

import com.example.studentplatform.model.QuizManagementQuestion;
import com.example.studentplatform.service.QuizManagementQuestionService;

@RestController
@RequestMapping("/api/quiz-questions")
@CrossOrigin
public class QuizManagementQuestionController {
	private final QuizManagementQuestionService service;

	public QuizManagementQuestionController(QuizManagementQuestionService service) {
		this.service = service;
	}

	@GetMapping
	public List<QuizManagementQuestion> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public QuizManagementQuestion getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public QuizManagementQuestion create(@RequestBody QuizManagementQuestion question) {
		return service.save(question);
	}

	@PutMapping("/{id}")
	public QuizManagementQuestion update(@PathVariable Integer id, @RequestBody QuizManagementQuestion question) {
		question.setId(id);
		return service.save(question);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
