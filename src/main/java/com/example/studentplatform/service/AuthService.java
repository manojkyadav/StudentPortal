package com.example.studentplatform.service;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Student;
import com.example.studentplatform.repository.StudentRepository;

@Service
public class AuthService {
	private final StudentRepository studentRepo;
	private final BCryptPasswordEncoder encoder;

	public AuthService(StudentRepository studentRepo, BCryptPasswordEncoder encoder) {
		this.studentRepo = studentRepo;
		this.encoder = encoder;
	}

	/*public Student register(Student s, String rawPassword) {
		Optional<Student> ex = studentRepo.findByEmail(s.getEmail());

		if (ex.isPresent())
			throw new RuntimeException("Email already exists");

		return studentRepo.save(s);
	}

	public Student login(String email, String rawPassword) {
		Optional<Student> opt = studentRepo.findByEmail(email);
		if (opt.isEmpty())
			throw new RuntimeException("Invalid credentials");
		Student u = opt.get();
		if (!encoder.matches(rawPassword, u.getPasswordHash()))
			throw new RuntimeException("Invalid credentials");
		u.setPasswordHash(null);
		return u;
	}*/
}
