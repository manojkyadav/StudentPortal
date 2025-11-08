package com.example.studentplatform.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentplatform.model.Leaderboard;
import com.example.studentplatform.service.LeaderboardService;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin
public class LeaderboardController {
	private final LeaderboardService service;

	public LeaderboardController(LeaderboardService service) {
		this.service = service;
	}

	@GetMapping
	public List<Leaderboard> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Leaderboard getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Leaderboard create(@RequestBody Leaderboard entry) {
		return service.save(entry);
	}

	@PutMapping("/{id}")
	public Leaderboard update(@PathVariable Integer id, @RequestBody Leaderboard entry) {
		entry.setStudentId(id);
		return service.save(entry);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
