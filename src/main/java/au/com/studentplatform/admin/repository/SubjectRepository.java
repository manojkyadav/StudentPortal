package au.com.studentplatform.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
	 // Get all subjects for a given classId
    List<Subject> findByClassRoomId(int classId);
}
