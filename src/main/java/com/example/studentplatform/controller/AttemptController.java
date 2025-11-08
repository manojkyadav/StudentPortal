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

import com.example.studentplatform.model.Attempt;
import com.example.studentplatform.service.AttemptService;

@RestController
@RequestMapping("/api/attempts")
@CrossOrigin
public class AttemptController {
	private final AttemptService service;

	public AttemptController(AttemptService service) {
		this.service = service;
	}

	@GetMapping
	public List<Attempt> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Attempt getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Attempt create(@RequestBody Attempt attempt) {
		return service.save(attempt);
	}

	@PutMapping("/{id}")
	public Attempt update(@PathVariable Integer id, @RequestBody Attempt attempt) {
		attempt.setId(id);
		return service.save(attempt);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
