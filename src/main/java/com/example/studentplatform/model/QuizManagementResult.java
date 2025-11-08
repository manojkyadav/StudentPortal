package com.example.studentplatform.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_management_result")
public class QuizManagementResult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// FK
	@Column(name = "student_id", nullable = false)
	private Integer studentId;

	@Column(name = "quiz_id", nullable = false)
	private Integer quizId;

	@Column(name = "total_questions")
	private Integer totalQuestions = 0;

	@Column(name = "correct_answers")
	private Integer correctAnswers = 0;

	@Column(name = "score_percentage", precision = 5, scale = 2)
	private java.math.BigDecimal scorePercentage = java.math.BigDecimal.ZERO;

	@Column(name = "completed_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime completedAt;

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
	 * @return the quizId
	 */
	public Integer getQuizId() {
		return quizId;
	}

	/**
	 * @param quizId the quizId to set
	 */
	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}

	/**
	 * @return the totalQuestions
	 */
	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	/**
	 * @param totalQuestions the totalQuestions to set
	 */
	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	/**
	 * @return the correctAnswers
	 */
	public Integer getCorrectAnswers() {
		return correctAnswers;
	}

	/**
	 * @param correctAnswers the correctAnswers to set
	 */
	public void setCorrectAnswers(Integer correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	/**
	 * @return the scorePercentage
	 */
	public java.math.BigDecimal getScorePercentage() {
		return scorePercentage;
	}

	/**
	 * @param scorePercentage the scorePercentage to set
	 */
	public void setScorePercentage(java.math.BigDecimal scorePercentage) {
		this.scorePercentage = scorePercentage;
	}

	/**
	 * @return the completedAt
	 */
	public LocalDateTime getCompletedAt() {
		return completedAt;
	}

	/**
	 * @param completedAt the completedAt to set
	 */
	public void setCompletedAt(LocalDateTime completedAt) {
		this.completedAt = completedAt;
	}
	
}
