package au.com.studentplatform.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.Topic;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	// List<Question> findByTopicsId(Integer topicId);
	/*
	 * =============================== Practice Mode – All questions
	 * ===============================
	 */
	//List<Question> findByTopic(Integer topicId);
	List<Question> findByTopic(Topic topicId);

	/*
	 * =============================== Exam Mode – Random questions
	 * ===============================
	 */

	// ✅ MySQL / PostgreSQL
	// @Query( value = " SELECT * FROM questions WHERE topic_id = :topicId ORDER BY
	// RANDOM() LIMIT 20", nativeQuery = true)
	/*@Query("""
			    SELECT q
			FROM Question q
			WHERE q.topic.id = :topicId
			ORDER BY function('RAND')
			 """)*/
	@Query("""
		    SELECT DISTINCT q
		    FROM Question q
		    JOIN FETCH q.options
		    WHERE q.topic.id = :topicId
		    ORDER BY function('RAND')
		""")
	List<Question> findRandomQuestions(@Param("topicId") Integer topicId, Pageable pageable);
	@Query("""
		    SELECT q
		    FROM Question q
		    JOIN FETCH q.options
		    WHERE q.id = :id
		""")
		Optional<Question> findByIdWithOptions(@Param("id") Integer id);
	
	//------temp---------------
	@Query("""
		    SELECT q.id
		    FROM Question q
		    WHERE q.topic.id = :topicId
		    ORDER BY function('RAND')
		""")
		List<Integer> findRandomQuestionIds(
		        @Param("topicId") Integer topicId,
		        Pageable pageable
		);
	@Query("""
		    SELECT DISTINCT q
		    FROM Question q
		    LEFT JOIN FETCH q.options
		    WHERE q.id IN :ids
		""")
		List<Question> findQuestionsWithOptions(@Param("ids") List<Integer> ids);

}
