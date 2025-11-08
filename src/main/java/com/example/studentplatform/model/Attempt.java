package com.example.studentplatform.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attempt")
public class Attempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// FK
	@Column(name = "student_id", nullable = false)
	private Integer studentId;

	@Column(name = "question_id", nullable = false)
	private Integer questionId;

	@Column(name = "chosen_index", length = 5)
	private String chosenIndex;

	@Column
	private Boolean correct;

	@Column(precision = 5, scale = 2)
	private BigDecimal score = BigDecimal.ZERO;

	@Column(name = "answered_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime answeredAt;

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
	 * @return the questionId
	 */
	public Integer getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the chosenIndex
	 */
	public String getChosenIndex() {
		return chosenIndex;
	}

	/**
	 * @param chosenIndex the chosenIndex to set
	 */
	public void setChosenIndex(String chosenIndex) {
		this.chosenIndex = chosenIndex;
	}

	/**
	 * @return the correct
	 */
	public Boolean getCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	/**
	 * @return the score
	 */
	public BigDecimal getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(BigDecimal score) {
		this.score = score;
	}

	/**
	 * @return the answeredAt
	 */
	public LocalDateTime getAnsweredAt() {
		return answeredAt;
	}

	/**
	 * @param answeredAt the answeredAt to set
	 */
	public void setAnsweredAt(LocalDateTime answeredAt) {
		this.answeredAt = answeredAt;
	}

	
}