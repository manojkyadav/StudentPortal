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

import com.example.studentplatform.model.StudentProgress;
import com.example.studentplatform.service.StudentProgressService;

@RestController
@RequestMapping("/api/progress")
@CrossOrigin
public class StudentProgressController {
	private final StudentProgressService service;

	public StudentProgressController(StudentProgressService service) {
		this.service = service;
	}

	@GetMapping
	public List<StudentProgress> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public StudentProgress getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public StudentProgress create(@RequestBody StudentProgress progress) {
		return service.save(progress);
	}

	@PutMapping("/{id}")
	public StudentProgress update(@PathVariable Integer id, @RequestBody StudentProgress progress) {
		progress.setId(id);
		return service.save(progress);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
