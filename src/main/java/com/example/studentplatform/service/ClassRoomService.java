package com.example.studentplatform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentplatform.model.ClassRoom;
import com.example.studentplatform.repository.ClassRoomRepository;

@Service
@Transactional
public class ClassRoomService {
	@Autowired
	private final ClassRoomRepository repo;

	public ClassRoomService(ClassRoomRepository repo) {
		this.repo = repo;
	}

	public List<ClassRoom> findAll() {
		return repo.findAll();
	}

	public ClassRoom findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("ClassRoom not found"));
	}

	public ClassRoom save(ClassRoom s) {
		return repo.save(s);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
}
