package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Leaderboard;

public interface LeaderboardRepository extends JpaRepository<Leaderboard, Integer> {
}
