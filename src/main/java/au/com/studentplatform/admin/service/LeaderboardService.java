package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Leaderboard;
import au.com.studentplatform.admin.repository.LeaderboardRepository;

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
