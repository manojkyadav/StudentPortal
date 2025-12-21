package au.com.studentplatform.admin.model;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StudentAttempt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Question question;

	private Integer selectedOptionId;
	private boolean correct;
	private LocalDateTime attemptTime = LocalDateTime.now();

	// getters / setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getSelectedOptionId() {
		return selectedOptionId;
	}

	public void setSelectedOptionId(Integer selectedOptionId) {
		this.selectedOptionId = selectedOptionId;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public LocalDateTime getAttemptTime() {
		return attemptTime;
	}

	public void setAttemptTime(LocalDateTime attemptTime) {
		this.attemptTime = attemptTime;
	}
}
