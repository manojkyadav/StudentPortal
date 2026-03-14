package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.User;
import au.com.studentplatform.admin.model.UserRole;
import au.com.studentplatform.admin.model.UserStatus;
import au.com.studentplatform.admin.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
    	this.userRepository =  userRepository;
    	
    }
    // Create user / Teacher / Admin
    public User createuser(
            String name,
            String email,
            String rawPassword,
            UserRole role,
            Integer classRoomId
    ) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setStatus(UserStatus.ACTIVE);
        user.setClassRoomId(classRoomId);
        

        return userRepository.save(user);
    }

    public User updateuser(Integer id, String name, Integer classRoomId) {
    	User user = getById(id);
        user.setName(name);
        user.setClassRoomId(classRoomId);
        return userRepository.save(user);
    }

    public User getById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public List<User> getByRole(UserRole role) {
        return userRepository.findByRole(role);
    }

    public void suspenduser(Integer id) {
    	User user = getById(id);
        user.setStatus(UserStatus.SUSPENDED);
        userRepository.save(user);
    }

    // Soft delete
    public void deleteuser(Integer id) {
    	User user = getById(id);
        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }

    public void updateLastLogin(Integer id) {
    	User user = getById(id);
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }
}
