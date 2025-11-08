package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.StudentProgress;

public interface StudentProgressRepository extends JpaRepository<StudentProgress, Integer> {
}
