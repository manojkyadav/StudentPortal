package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.QuizManagementQuestion;

public interface QuizManagementQuestionRepository extends JpaRepository<QuizManagementQuestion, Integer> {
}
