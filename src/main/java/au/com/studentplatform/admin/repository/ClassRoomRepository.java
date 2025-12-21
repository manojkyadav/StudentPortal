package au.com.studentplatform.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.studentplatform.admin.model.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Integer> {
}
