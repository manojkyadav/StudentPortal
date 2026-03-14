package au.com.studentplatform.admin.service;

import au.com.studentplatform.admin.model.User;

//@Service
public interface AuthService {
	User authenticate(String email, String password);
}
