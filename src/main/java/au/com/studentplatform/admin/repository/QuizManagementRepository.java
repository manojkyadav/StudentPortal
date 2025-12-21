package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.QuizManagement;

public interface QuizManagementRepository extends JpaRepository<QuizManagement, Integer> {
}
