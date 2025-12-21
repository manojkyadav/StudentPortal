package au.com.studentplatform.admin.model.dto;

public class DashboardStatsDTO {
	private long testsCompleted;
	private double averageScore;
	private long topicsStudied;

	public long getTestsCompleted() {
		return testsCompleted;
	}

	public void setTestsCompleted(long testsCompleted) {
		this.testsCompleted = testsCompleted;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public long getTopicsStudied() {
		return topicsStudied;
	}

	public void setTopicsStudied(long topicsStudied) {
		this.topicsStudied = topicsStudied;
	}

}
