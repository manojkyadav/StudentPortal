package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Country;
import com.example.studentplatform.repository.CountryRepository;

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
