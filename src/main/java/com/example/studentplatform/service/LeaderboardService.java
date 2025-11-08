package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Leaderboard;
import com.example.studentplatform.repository.LeaderboardRepository;

@Service
public class LeaderboardService {
	private final LeaderboardRepository repo;

	public LeaderboardService(LeaderboardRepository repo) {
		this.repo = repo;
	}

	public List<Leaderboard> findAll() {
		return repo.findAll();
	}

	public Leaderboard findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Leaderboard entry not found"));
	}

	public Leaderboard save(Leaderboard entry) {
		return repo.save(entry);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
