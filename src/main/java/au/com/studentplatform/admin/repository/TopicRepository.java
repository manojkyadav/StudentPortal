package au.com.studentplatform.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
	 List<Topic> findBySubjectId(int subjectId);
}
