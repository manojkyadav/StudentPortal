package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Student;
import com.example.studentplatform.repository.StudentRepository;

@Service
public class StudentService {
	private final StudentRepository repo;

	public StudentService(StudentRepository repo) {
		this.repo = repo;
	}

	public List<Student> findAll() {
		return repo.findAll();
	}

	public Student findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public Student save(Student student) {
		return repo.save(student);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
