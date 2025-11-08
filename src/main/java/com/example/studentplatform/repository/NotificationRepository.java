package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
