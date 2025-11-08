package com.example.studentplatform.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.studentplatform.model.Subject;
import com.example.studentplatform.repository.SubjectRepository;

@Service
@Transactional
public class SubjectService {
	private final SubjectRepository repo;

	public SubjectService(SubjectRepository repo) {
		this.repo = repo;
	}

	public List<Subject> findAll() {
		return repo.findAll();
	}

	public Subject findById(Integer id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));
	}

	public Subject save(Subject s) {
		return repo.save(s);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}

	public List<Subject> getSubjectsByClassId(int classId) {
		return repo.findByClassRoomId(classId);
	}

}
