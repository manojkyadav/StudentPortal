package au.com.studentplatform.admin.model.dto;

import java.time.LocalDateTime;

import au.com.studentplatform.admin.model.TestMode;

public class RecentActivityDTO {

	private Integer topicId;
	private String topicName;
	private TestMode mode;
	private Double score;
	private LocalDateTime completedAt;

	public RecentActivityDTO(Integer topicId, String topicName, TestMode mode, Double score, LocalDateTime completedAt) {
		this.topicId = topicId;
		this.mode = mode;
		this.score = score;
		this.completedAt = completedAt;
		this.topicName=topicName;
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

}
