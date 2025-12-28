package au.com.studentplatform.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.studentplatform.admin.model.CreateStudentRequest;
import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.TestStatus;
import au.com.studentplatform.admin.model.UpdateStudentRequest;
import au.com.studentplatform.admin.model.UserRole;
import au.com.studentplatform.admin.repository.NotificationRepository;
import au.com.studentplatform.admin.repository.StudentRepository;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.StudentService;
import jakarta.servlet.http.HttpSession;
import au.com.studentplatform.admin.service.TestSessionService;

@Controller
@RequestMapping("/app")
public class StudentController {

	private final StudentService studentService;
	private final TestSessionService testSessionService;
	
	@Autowired
	NotificationRepository notificationRepo;
	@Autowired StudentRepository studentRepo;

	public StudentController(StudentService studentService,  TestSessionService testSessionService) {
		this.studentService = studentService;
		this.testSessionService = testSessionService;
	}
	
	
	 @GetMapping("/student/progress")
    public String getProgress(Model model , HttpSession session) {
		 
		 String email = (String) session.getAttribute("USER_EMAIL");
		 
        model.addAttribute("examsession",testSessionService.getSubjectsByClassId(email, TestStatus.SUBMITTED));
        model.addAttribute("recentActivities",testSessionService.getRecentActivities(email));
        model.addAttribute("activePage", "progress");
        
        return  "students/progress";
    }
	
	  @PostMapping("/register/student")
	    public String createStudent(@ModelAttribute CreateStudentRequest request) {
	         studentService.createStudent(
	                request.getName(),
	                request.getEmail(),
	                request.getPassword(),
	                request.getRole(),
	                request.getClassRoomId()
	        );
	        return "students/dashboard";
	    }

	    @GetMapping
	    public List<Student> getAll() {
	        return studentService.getAll();
	    }

	    @GetMapping("/{id}")
	    public Student getById(@PathVariable Integer id) {
	        return studentService.getById(id);
	    }

	    @GetMapping("/role/{role}")
	    public List<Student> getByRole(@PathVariable UserRole role) {
	        return studentService.getByRole(role);
	    }

	    @PutMapping("/{id}")
	    public Student update(
	            @PathVariable Integer id,
	            @RequestBody UpdateStudentRequest request
	    ) {
	        return studentService.updateStudent(
	                id,
	                request.getName(),
	                request.getClassRoomId()
	        );
	    }

	    @PutMapping("/{id}/suspend")
	    public void suspend(@PathVariable Integer id) {
	        studentService.suspendStudent(id);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Integer id) {
	        studentService.deleteStudent(id);
	    }
/*
	// ---------- STUDENTS ----------
		@GetMapping("/students")
		public String studentsList(Model model) {
			model.addAttribute("students", studentService.findAll());
			return "students/list";
		}

		@GetMapping("/students/new")
		public String newStudentForm(Model model) {
			model.addAttribute("student", new Student());

			List<ClassRoom> classroomList = classroomService.findAll();
			model.addAttribute("classroomList", classroomList);
			return "students/form";
		}

		@GetMapping("/students/edit/{id}")
		public String editStudent(@PathVariable Integer id, Model model) {
			model.addAttribute("student", studentService.findById(id));

			List<ClassRoom> classroomList = classroomService.findAll();
			model.addAttribute("classroomList", classroomList);

			return "students/form";
		}

		@PostMapping("/students/save")
		public String saveStudent(@ModelAttribute Student student, RedirectAttributes ra) {
			studentService.save(student);
			ra.addFlashAttribute("success", "Student saved");
			return "redirect:/app/students";
		}

		@GetMapping("/students/delete/{id}")
		public String deleteStudent(@PathVariable Integer id, RedirectAttributes ra) {
			studentService.deleteById(id);
			ra.addFlashAttribute("success", "Student deleted");
			return "redirect:/app/students";
		}*/
}
