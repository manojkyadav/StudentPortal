package au.com.studentplatform.admin.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Student {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    @Column(name = "created_at")
	    private LocalDateTime createdAt;

	    @Column(unique = true)
	    private String email;

	    @Column(name = "last_login")
	    private LocalDateTime lastLogin;

	    private String name;

	    @Column(name = "password_hash", nullable = false)
	    private String passwordHash;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserRole role;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private UserStatus status;

	    @Column(name = "classRoom_id")
	    private Integer classRoomId;

	    @PrePersist
	    public void onCreate() {
	        this.createdAt = LocalDateTime.now();
	        this.status = UserStatus.ACTIVE;
	    }

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public LocalDateTime getLastLogin() {
			return lastLogin;
		}

		public void setLastLogin(LocalDateTime lastLogin) {
			this.lastLogin = lastLogin;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPasswordHash() {
			return passwordHash;
		}

		public void setPasswordHash(String passwordHash) {
			this.passwordHash = passwordHash;
		}

		public UserRole getRole() {
			return role;
		}

		public void setRole(UserRole role) {
			this.role = role;
		}

		public UserStatus getStatus() {
			return status;
		}

		public void setStatus(UserStatus status) {
			this.status = status;
		}

		public Integer getClassRoomId() {
			return classRoomId;
		}

		public void setClassRoomId(Integer classRoomId) {
			this.classRoomId = classRoomId;
		}
}
