/**
 * 
 */
package au.com.studentplatform.admin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/**
 * 
 */
@Entity
@Table(name = "test_session")
public class TestSession {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "user_id", nullable = false)
	    private String email;

	    @Column(name = "topic_id", nullable = false)
	    private Integer topicId;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TestMode mode;

	    @Column(name = "start_time", nullable = false)
	    private LocalDateTime startTime;

	    @Column(name = "end_time")
	    private LocalDateTime endTime;

	    @Column(name = "total_marks")
	    private Integer totalMarks;

	    @Column(name = "obtained_marks")
	    private Integer obtainedMarks;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TestStatus status;

	    /* ======================
	       Constructors
	       ====================== */

	    public TestSession() {
	    }

	    public TestSession(Integer id, String email, Integer topicId, TestMode mode) {
	        this.id=id;
	    	this.email = email;
	        this.topicId = topicId;
	        this.mode = mode;
	        this.startTime = LocalDateTime.now();
	        this.status = TestStatus.STARTED;
	    }

	    /* ======================
	       Getters & Setters
	       ====================== */

	    public Integer getId() {
	        return id;
	    }

	   
	    public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getTopicId() {
	        return topicId;
	    }

	    public void setTopicId(Integer topicId) {
	        this.topicId = topicId;
	    }

	    public TestMode getMode() {
	        return mode;
	    }

	    public void setMode(TestMode mode) {
	        this.mode = mode;
	    }

	    public LocalDateTime getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(LocalDateTime startTime) {
	        this.startTime = startTime;
	    }

	    public LocalDateTime getEndTime() {
	        return endTime;
	    }

	    public void setEndTime(LocalDateTime endTime) {
	        this.endTime = endTime;
	    }

	    public Integer getTotalMarks() {
	        return totalMarks;
	    }

	    public void setTotalMarks(Integer totalMarks) {
	        this.totalMarks = totalMarks;
	    }

	    public Integer getObtainedMarks() {
	        return obtainedMarks;
	    }

	    public void setObtainedMarks(Integer obtainedMarks) {
	        this.obtainedMarks = obtainedMarks;
	    }

	    public TestStatus getStatus() {
	        return status;
	    }

	    public void setStatus(TestStatus status) {
	        this.status = status;
	    }

}
