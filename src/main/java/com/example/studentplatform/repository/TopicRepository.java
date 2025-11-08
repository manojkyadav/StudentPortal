package com.example.studentplatform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
	 List<Topic> findBySubjectId(int subjectId);
}
