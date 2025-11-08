package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.QuizManagementQuestion;

public interface QuizManagementQuestionRepository extends JpaRepository<QuizManagementQuestion, Integer> {
}
