package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.UserStatus;
import au.com.studentplatform.admin.repository.StudentRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            StudentRepository studentRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Student authenticate(String email, String password) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (student.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }

        if (!passwordEncoder.matches(password, student.getPasswordHash())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Update last login
        student.setLastLogin(LocalDateTime.now());
        studentRepository.save(student);

        return student;
    }
}