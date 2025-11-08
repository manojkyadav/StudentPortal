package com.example.studentplatform.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.studentplatform.model.Subject;
import com.example.studentplatform.model.Topic;
import com.example.studentplatform.repository.TopicRepository;

@Service
public class TopicService {
	private final TopicRepository repo;

	public TopicService(TopicRepository repo) {
		this.repo = repo;
	}

	public List<Topic> findAll() {
		return repo.findAll();
	}

	public Topic findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
	}

	public Topic save(Topic topic) {
		return repo.save(topic);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	public List<Topic> getTopicBySubjectId(int subjectId) {
		return repo.findBySubjectId(subjectId);
	}

}
