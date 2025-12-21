package au.com.studentplatform.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.repository.ClassRoomRepository;

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
