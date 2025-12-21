package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
