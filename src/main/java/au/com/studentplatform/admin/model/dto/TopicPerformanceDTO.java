package au.com.studentplatform.admin.model.dto;

public class TopicPerformanceDTO {

	private Integer topicId;
	private Double averageScore;

	public TopicPerformanceDTO(Integer topicId, Double averageScore) {
		this.topicId = topicId;
		this.averageScore = averageScore;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public Double getAverageScore() {
		return averageScore;
	}

}
