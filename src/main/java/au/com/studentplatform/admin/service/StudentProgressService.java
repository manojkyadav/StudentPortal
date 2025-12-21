package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.StudentProgress;
import au.com.studentplatform.admin.repository.StudentProgressRepository;

@Service
public class StudentProgressService {
	private final StudentProgressRepository repo;

	public StudentProgressService(StudentProgressRepository repo) {
		this.repo = repo;
	}

	public List<StudentProgress> findAll() {
		return repo.findAll();
	}

	public StudentProgress findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Student progress not found"));
	}

	public StudentProgress save(StudentProgress progress) {
		return repo.save(progress);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
