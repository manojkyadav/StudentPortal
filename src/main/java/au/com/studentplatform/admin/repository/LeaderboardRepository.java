package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Leaderboard;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {
}
