package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.QuizManagementResult;

public interface QuizManagementResultRepository extends JpaRepository<QuizManagementResult, Integer> {
}
