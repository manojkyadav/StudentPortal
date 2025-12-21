package au.com.studentplatform.admin.model;

public class CreateStudentRequest {

	private String name;
	private String email;
	private String password;
	private UserRole role;
	private Integer classRoomId;
	
	private String confirmPassword;

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Integer getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}

}
