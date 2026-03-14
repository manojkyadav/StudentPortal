package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.User;
import au.com.studentplatform.admin.model.UserStatus;
import au.com.studentplatform.admin.repository.UserRepository;

@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new RuntimeException("Account is not active");
        }

		/*
		 * if (!passwordEncoder.matches(password, user.getPasswordHash())) { throw new
		 * RuntimeException("Invalid email or password"); }
		 */

        // Update last login
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPasswordHash())
                .roles(user.getRole().name())
                .build();
    }

}
