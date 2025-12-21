package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
}
