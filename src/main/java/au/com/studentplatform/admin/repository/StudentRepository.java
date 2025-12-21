package au.com.studentplatform.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.UserRole;
import au.com.studentplatform.admin.model.UserStatus;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	//Student findByEmailAndPassword(String email, String password);
	
	Optional<Student> findByEmail(String email);

    List<Student> findByRole(UserRole role);

    List<Student> findByStatus(UserStatus status);

    List<Student> findByClassRoomId(Integer classRoomId);

    boolean existsByEmail(String email);
}
