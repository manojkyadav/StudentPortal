package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Country;
import au.com.studentplatform.admin.repository.CountryRepository;

@Service
public class CountryService {
	private final CountryRepository repo;

	public CountryService(CountryRepository repo) {
		this.repo = repo;
	}

	public List<Country> findAll() {
		return repo.findAll();
	}

	public Country findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
	}

	public Country save(Country c) {
		return repo.save(c);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
