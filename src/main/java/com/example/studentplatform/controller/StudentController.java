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

import com.example.studentplatform.model.Student;
import com.example.studentplatform.service.StudentService;

@RestController
@RequestMapping("/api/students")
@CrossOrigin
public class StudentController {
	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Student> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Student getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Student create(@RequestBody Student student) {
		return service.save(student);
	}

	@PutMapping("/{id}")
	public Student update(@PathVariable Integer id, @RequestBody Student student) {
		student.setId(id);
		return service.save(student);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
