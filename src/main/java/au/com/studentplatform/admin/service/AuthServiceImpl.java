package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.User;
import au.com.studentplatform.admin.model.UserStatus;
//import au.com.studentplatform.admin.repository.StudentRepository;
import au.com.studentplatform.admin.repository.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
    		UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User authenticate(String email, String password) {

    	User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Invalid email or password"));

        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }

     //   if (!passwordEncoder.matches(password, user.getPasswordHash())) {
     //       throw new RuntimeException("Invalid email or password");
     //   }

        // Update last login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return user;
    }
}