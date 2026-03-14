package au.com.studentplatform.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.User;
import au.com.studentplatform.admin.model.UserRole;
import au.com.studentplatform.admin.model.UserStatus;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);

    List<User> findByRole(UserRole role);

    List<User> findByStatus(UserStatus status);

    List<User> findByClassRoomId(Integer classRoomId);

    boolean existsByEmail(String email);

}
