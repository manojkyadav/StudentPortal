package com.example.studentplatform.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// FKs
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "class_id")
	private ClassRoom classRoom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	private Subject subject;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "topic_id")
	private Topic topics;

	@Lob
	@Column(name = "question_txt", nullable = false)
	private String questionTxt;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType = QuestionType.MULTIPLE_CHOICE;
	
	@OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionOption> options = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	@Column(name = "difficulty_level", nullable = false)
	private Difficulty difficultyLevel = Difficulty.MEDIUM;

	@Column(name = "correct_index", length = 5)
	private String correctIndex;

	@Lob
	private String explanation;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP")
	private LocalDateTime createdAt;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status = Status.ACTIVE;

	private String imageUrl;
	private String videoUrl;

	public enum Difficulty {
		EASY, MEDIUM, HARD
	}
	
	public enum QuestionType {
		MULTIPLE_CHOICE, TRUE_FALSE, SHORT_ANSWER, FILL_IN_THE_BLANK, MATCHING
	}

	/**
	 * @return the questionType
	 */
	public QuestionType getQuestionType() {
		return questionType;
	}

	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public enum Status {
		ACTIVE, INACTIVE
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
	 * @return the classRoom
	 */
	public ClassRoom getClassRoom() {
		return classRoom;
	}

	/**
	 * @param classRoom the classRoom to set
	 */
	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	/**
	 * @return the subject
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	/**
	 * @return the topics
	 */
	public Topic getTopics() {
		return topics;
	}

	/**
	 * @param topics the topics to set
	 */
	public void setTopics(Topic topics) {
		this.topics = topics;
	}

	

	/**
	 * @return the questionTxt
	 */
	public String getQuestionTxt() {
		return questionTxt;
	}

	/**
	 * @param questionTxt the questionTxt to set
	 */
	public void setQuestionTxt(String questionTxt) {
		this.questionTxt = questionTxt;
	}

	/**
	 * @return the options
	 */
	public List<QuestionOption> getOptions() {
		return options;
	}

	/**
	 * @param options the options to set
	 */
	public void setOptions(List<QuestionOption> options) {
		this.options = options;
	}

	/**
	 * @return the difficultyLevel
	 */
	public Difficulty getDifficultyLevel() {
		return difficultyLevel;
	}

	/**
	 * @param difficultyLevel the difficultyLevel to set
	 */
	public void setDifficultyLevel(Difficulty difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	/**
	 * @return the correctIndex
	 */
	public String getCorrectIndex() {
		return correctIndex;
	}

	/**
	 * @param correctIndex the correctIndex to set
	 */
	public void setCorrectIndex(String correctIndex) {
		this.correctIndex = correctIndex;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the videoUrl
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * @param videoUrl the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	
}