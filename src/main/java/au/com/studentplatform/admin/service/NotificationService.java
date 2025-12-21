package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Notification;
import au.com.studentplatform.admin.repository.NotificationRepository;

@Service
public class NotificationService {
	private final NotificationRepository repo;

	public NotificationService(NotificationRepository repo) {
		this.repo = repo;
	}

	public List<Notification> findAll() {
		return repo.findAll();
	}

	public Notification findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
	}

	public Notification save(Notification notification) {
		return repo.save(notification);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
