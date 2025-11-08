package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.QuizManagementResult;

public interface QuizManagementResultRepository extends JpaRepository<QuizManagementResult, Integer> {
}
