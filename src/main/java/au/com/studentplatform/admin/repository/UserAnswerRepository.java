package au.com.studentplatform.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {

    Optional<UserAnswer> findBySessionIdAndQuestionId(Integer sessionId, Integer questionId);

    List<UserAnswer> findBySessionId(Integer sessionId);
}

