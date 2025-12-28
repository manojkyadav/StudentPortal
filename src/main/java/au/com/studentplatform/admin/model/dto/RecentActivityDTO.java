package au.com.studentplatform.admin.model.dto;

import java.time.LocalDateTime;

import au.com.studentplatform.admin.model.TestMode;

public class RecentActivityDTO {

	private Integer sessionId;
	private String subjectName;
	private Integer topicId;
	private String topicName;
	private TestMode mode;
	private Double score;
	private LocalDateTime completedAt;

	public RecentActivityDTO(Integer sessionId, String subjectName, Integer topicId, String topicName, TestMode mode, Double score, LocalDateTime completedAt) {
		this.topicId = topicId;
		this.mode = mode;
		this.score = score;
		this.completedAt = completedAt;
		this.topicName=topicName;
		this.sessionId=sessionId;
		this.subjectName=subjectName;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public void setMode(TestMode mode) {
		this.mode = mode;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public TestMode getMode() {
		return mode;
	}

	public Double getScore() {
		return score;
	}

	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

}
