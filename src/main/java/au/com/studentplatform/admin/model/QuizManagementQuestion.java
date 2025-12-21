package au.com.studentplatform.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_management_question")
public class QuizManagementQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	// FK to quiz
	@Column(name = "quiz_id", nullable = false)
	private Integer quizId;

	// FK to question
	@Column(name = "question_id", nullable = false)
	private Integer questionId;

	@Column(name = "question_order")
	private Integer questionOrder = 0;

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
	 * @return the questionOrder
	 */
	public Integer getQuestionOrder() {
		return questionOrder;
	}

	/**
	 * @param questionOrder the questionOrder to set
	 */
	public void setQuestionOrder(Integer questionOrder) {
		this.questionOrder = questionOrder;
	}
	
}
