package au.com.studentplatform.admin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_progress")
public class StudentProgress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// FK
	@Column(name = "student_id", nullable = false)
	private Integer studentId;

	@Column(name = "class_id")
	private Integer classRoomId;

	@Column(name = "subject_id")
	private Integer subjectId;

	@Column(name = "topic_id")
	private Integer topicId;

	private Boolean completed = false;

	@Column(name = "last_accessed", columnDefinition = "TIMESTAMP")
	private LocalDateTime lastAccessed;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the classRoomId
	 */
	public Integer getClassRoomId() {
		return classRoomId;
	}

	/**
	 * @param classRoomId the classRoomId to set
	 */
	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}

	/**
	 * @return the subjectId
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * @param subjectId the subjectId to set
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * @return the topicId
	 */
	public Integer getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the completed
	 */
	public Boolean getCompleted() {
		return completed;
	}

	/**
	 * @param completed the completed to set
	 */
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	/**
	 * @return the lastAccessed
	 */
	public LocalDateTime getLastAccessed() {
		return lastAccessed;
	}

	/**
	 * @param lastAccessed the lastAccessed to set
	 */
	public void setLastAccessed(LocalDateTime lastAccessed) {
		this.lastAccessed = lastAccessed;
	}
	
}
