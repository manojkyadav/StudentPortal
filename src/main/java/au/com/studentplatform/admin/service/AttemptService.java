package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Attempt;
import au.com.studentplatform.admin.repository.AttemptRepository;

@Service
public class AttemptService {
	private final AttemptRepository repo;

	public AttemptService(AttemptRepository repo) {
		this.repo = repo;
	}

	public List<Attempt> findAll() {
		return repo.findAll();
	}

	public Attempt findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Attempt not found"));
	}

	public Attempt save(Attempt attempt) {
		return repo.save(attempt);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
