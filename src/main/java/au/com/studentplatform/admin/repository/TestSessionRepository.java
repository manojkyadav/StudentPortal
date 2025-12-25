package au.com.studentplatform.admin.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import au.com.studentplatform.admin.model.TestSession;
import au.com.studentplatform.admin.model.dto.RecentActivityDTO;
import au.com.studentplatform.admin.model.dto.TopicPerformanceDTO;

public interface TestSessionRepository extends JpaRepository<TestSession, Integer> {

	/*
	 * =============================== Tests Completed
	 * ===============================
	 */
	@Query("""
			    SELECT COUNT(t)
			    FROM TestSession t
			    WHERE t.email = :email
			      AND t.status = au.com.studentplatform.admin.model.TestStatus.SUBMITTED
			""")
	long countCompletedTests(@Param("email") String email);

	/*
	 * =============================== Average Score ===============================
	 */
	@Query("""
			    SELECT AVG((t.obtainedMarks * 100.0) / t.totalMarks)
			    FROM TestSession t
			    WHERE t.email = :email
			      AND t.status = au.com.studentplatform.admin.model.TestStatus.SUBMITTED
			""")
	Double findAverageScore(@Param("email") String email);

	/*
	 * =============================== Topics Studied
	 * ===============================
	 */
	@Query("""
			    SELECT COUNT(DISTINCT t.topicId)
			    FROM TestSession t
			    WHERE t.email = :email
			      AND t.status = au.com.studentplatform.admin.model.TestStatus.SUBMITTED
			""")
	long countDistinctTopics(@Param("email") String email);

	/*
	 * =============================== Topic Performance
	 * ===============================
	 */
	@Query("""
			    SELECT new au.com.studentplatform.admin.model.dto.TopicPerformanceDTO(
			        t.topicId, topic.topicName,
			        AVG((t.obtainedMarks * 100.0) / t.totalMarks)
			    )
			    FROM TestSession t, Topic topic
			    WHERE t.email = :email
			      AND t.status = au.com.studentplatform.admin.model.TestStatus.SUBMITTED
			      AND t.topicId = topic.id
			    GROUP BY t.topicId
			""")
	List<TopicPerformanceDTO> topicPerformance(@Param("email") String email);

	/*
	 * =============================== Recent Activities
	 * ===============================
	 */
	@Query("""
			    SELECT new au.com.studentplatform.admin.model.dto.RecentActivityDTO(
			        t.topicId, topic.topicName,
			        t.mode,
			        (t.obtainedMarks * 100.0) / t.totalMarks,
			        t.endTime
			    )
			    FROM TestSession t, Topic topic
			    WHERE t.email = :email
			      AND t.status = au.com.studentplatform.admin.model.TestStatus.SUBMITTED
			       AND t.topicId = topic.id
			    ORDER BY t.endTime DESC
			""")
	List<RecentActivityDTO> recentActivities(@Param("email") String email, Pageable pageable);
}
