package au.com.studentplatform.admin.service;

import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Student;

//@Service
public interface AuthService {
    Student authenticate(String email, String password);
}
