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

import com.example.studentplatform.model.Notification;
import com.example.studentplatform.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {
	private final NotificationService service;

	public NotificationController(NotificationService service) {
		this.service = service;
	}

	@GetMapping
	public List<Notification> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Notification getById(@PathVariable Integer id) {
		return service.findById(id);
	}

	@PostMapping
	public Notification create(@RequestBody Notification notification) {
		return service.save(notification);
	}

	@PutMapping("/{id}")
	public Notification update(@PathVariable Integer id, @RequestBody Notification notification) {
		notification.setId(id);
		return service.save(notification);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		service.deleteById(id);
	}
}
