package au.com.studentplatform.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.UserRole;
import au.com.studentplatform.admin.model.UserStatus;
import au.com.studentplatform.admin.repository.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public StudentService(StudentRepository studentRepository) {
    	this.studentRepository =  studentRepository;
    	
    }
    // Create Student / Teacher / Admin
    public Student createStudent(
            String name,
            String email,
            String rawPassword,
            UserRole role,
            Integer classRoomId
    ) {
        if (studentRepository.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }

        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPasswordHash(passwordEncoder.encode(rawPassword));
        student.setRole(role);
        student.setStatus(UserStatus.ACTIVE);
        student.setClassRoomId(classRoomId);
        

        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, String name, Integer classRoomId) {
        Student student = getById(id);
        student.setName(name);
        student.setClassRoomId(classRoomId);
        return studentRepository.save(student);
    }

    public Student getById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public List<Student> getByRole(UserRole role) {
        return studentRepository.findByRole(role);
    }

    public void suspendStudent(Integer id) {
        Student student = getById(id);
        student.setStatus(UserStatus.SUSPENDED);
        studentRepository.save(student);
    }

    // Soft delete
    public void deleteStudent(Integer id) {
        Student student = getById(id);
        student.setStatus(UserStatus.DELETED);
        studentRepository.save(student);
    }

    public void updateLastLogin(Integer id) {
        Student student = getById(id);
        student.setLastLogin(LocalDateTime.now());
        studentRepository.save(student);
    }
}
