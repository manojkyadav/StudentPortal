package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.StudentProgress;

public interface StudentProgressRepository extends JpaRepository<StudentProgress, Integer> {
}
