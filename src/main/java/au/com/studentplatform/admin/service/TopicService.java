package au.com.studentplatform.admin.service;


import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Subject;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.repository.TopicRepository;

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
