package au.com.studentplatform.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import au.com.studentplatform.admin.model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {
	List<Option> findByQuestionId(Integer questionId);

	@Query("SELECT o.correct FROM Option o WHERE o.id = :optionId")
	Boolean isOptionCorrect(@Param("optionId") Integer optionId);
}
