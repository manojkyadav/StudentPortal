package au.com.studentplatform.admin.model;

public class UpdateStudentRequest {
	private String name;
	private Integer classRoomId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getClassRoomId() {
		return classRoomId;
	}

	public void setClassRoomId(Integer classRoomId) {
		this.classRoomId = classRoomId;
	}

}
