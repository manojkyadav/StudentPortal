package com.example.studentplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	 // Get all subjects for a given classId
    List<Subject> findByClassRoomId(int classId);
}
