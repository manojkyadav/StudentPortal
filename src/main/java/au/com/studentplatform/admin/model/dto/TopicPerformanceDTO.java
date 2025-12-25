package au.com.studentplatform.admin.model.dto;

public class TopicPerformanceDTO {

	private Integer topicId;
	private String topicName;
	private Double averageScore;

	public TopicPerformanceDTO(Integer topicId, String topicName, Double averageScore) {
		this.topicId = topicId;
		this.averageScore = averageScore;
		this.topicName = topicName;
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

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public Double getAverageScore() {
		return averageScore;
	}

}
