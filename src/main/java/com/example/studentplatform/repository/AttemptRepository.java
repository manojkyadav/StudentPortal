package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
}
