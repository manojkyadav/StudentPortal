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

import com.example.studentplatform.model.Question;
import com.example.studentplatform.service.QuestionService;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin
public class QuestionController {
	private final QuestionService service;

	public QuestionController(QuestionService service) {
		this.service = service;
	}

	@GetMapping
	public List<Question> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Question getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Question create(@RequestBody Question question) {
		return service.save(question);
	}

	@PutMapping("/{id}")
	public Question update(@PathVariable Integer id, @RequestBody Question question) {
		question.setId(id);
		return service.save(question);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
