package au.com.studentplatform.admin.model.dto;

import java.time.LocalDateTime;

import au.com.studentplatform.admin.model.TestMode;

public class RecentActivityDTO {

	private Integer topicId;
	private TestMode mode;
	private Double score;
	private LocalDateTime completedAt;

	public RecentActivityDTO(Integer topicId, TestMode mode, Double score, LocalDateTime completedAt) {
		this.topicId = topicId;
		this.mode = mode;
		this.score = score;
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
