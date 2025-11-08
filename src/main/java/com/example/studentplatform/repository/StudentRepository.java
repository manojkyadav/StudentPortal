package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
