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

import com.example.studentplatform.model.QuestionOption;
import com.example.studentplatform.service.OptionService;

@RestController
@RequestMapping("/api/options")
@CrossOrigin
public class OptionController {
	private final OptionService service;

	public OptionController(OptionService service) {
		this.service = service;
	}

	@GetMapping
	public List<QuestionOption> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public QuestionOption getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public QuestionOption create(@RequestBody QuestionOption option) {
		return service.save(option);
	}

	@PutMapping("/{id}")
	public QuestionOption update(@PathVariable Integer id, @RequestBody QuestionOption option) {
		option.setId(id);
		return service.save(option);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
