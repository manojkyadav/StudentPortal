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

import com.example.studentplatform.model.Topic;
import com.example.studentplatform.service.TopicService;

@RestController
@RequestMapping("/api/topics")
@CrossOrigin
public class TopicController {
	private final TopicService service;

	public TopicController(TopicService service) {
		this.service = service;
	}

	@GetMapping
	public List<Topic> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Topic getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Topic create(@RequestBody Topic topic) {
		return service.save(topic);
	}

	@PutMapping("/{id}")
	public Topic update(@PathVariable Integer id, @RequestBody Topic topic) {
		topic.setId(id);
		return service.save(topic);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
