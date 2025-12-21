package au.com.studentplatform.admin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "leaderboard")
public class Leaderboard {
	// Primary key is student_id (maps to Student)
	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "student_id")
	private Integer studentId;

	private Integer points = 0;

	@Column(name = "student_rank")
	private Integer studentRank;

	@ManyToOne
    private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Column(name = "last_updated", columnDefinition = "TIMESTAMP")
	private LocalDateTime lastUpdated;

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
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}

	/**
	 * @return the studentRank
	 */
	public Integer getStudentRank() {
		return studentRank;
	}

	/**
	 * @param studentRank the studentRank to set
	 */
	public void setStudentRank(Integer studentRank) {
		this.studentRank = studentRank;
	}

	/**
	 * @return the lastUpdated
	 */
	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
}
