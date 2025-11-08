package com.example.studentplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentplatform.model.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {
}
