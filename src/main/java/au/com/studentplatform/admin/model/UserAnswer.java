package au.com.studentplatform.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_answer")
public class UserAnswer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "session_id", nullable = false)
    private Integer sessionId;

    //@Column(name = "question_id", nullable = false)
    //private Integer questionId;

   // @Column(name = "selected_option_id", nullable = false)
   // private Integer selectedOptionId;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;
    
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "option_id")
    private Option option;
    

    /* ======================
       Constructors
       ====================== */

    public UserAnswer() {
    }

    public UserAnswer(Integer sessionId, Question question, Option option, Boolean isCorrect) {
        this.sessionId = sessionId;
        this.question = question;
        this.option = option;
        this.isCorrect = isCorrect;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSessionId() {
		return sessionId;
	}

	public void setSessionId(Integer sessionId) {
		this.sessionId = sessionId;
	}

	public Boolean getIsCorrect() {
		return isCorrect;
	}

	public void setIsCorrect(Boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

    /* ======================
       Getters & Setters
       ====================== */

    

}
