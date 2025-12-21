package au.com.studentplatform.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.StudentAttempt;

public interface StudentAttemptRepository  extends JpaRepository<StudentAttempt, Integer> {

	List<StudentAttempt> findByStudent(Student student);
    List<StudentAttempt> findByStudentAndQuestion_TopicId(Student student, Integer topicId);
}