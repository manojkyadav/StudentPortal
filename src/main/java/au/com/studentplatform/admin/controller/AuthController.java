package au.com.studentplatform.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.studentplatform.admin.model.CreateStudentRequest;
import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.dto.LoginRequestDto;
import au.com.studentplatform.admin.service.AuthService;
import au.com.studentplatform.admin.service.ClassRoomService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AuthController {

	private final AuthService authService;
	private final ClassRoomService classRoomService;

	public AuthController(AuthService authService, ClassRoomService classRoomService) {
		this.authService = authService;
		this.classRoomService=classRoomService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("createStudentRequest", new CreateStudentRequest());
		model.addAttribute("classRoomList", classRoomService.findAll());
		
		return "register";
	}

	
	// -------- Show Login Page --------
	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginRequest", new LoginRequestDto());
		return "login";
	}

	// -------- Process Login --------
	@PostMapping("/login")
	public String processLogin(@ModelAttribute("loginRequest") LoginRequestDto loginRequest, HttpSession session,
			Model model) {
		try {
			Student student = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
			
			if (student == null) {
		        model.addAttribute("error", "Invalid email or password");
		        return "login";
		    }

			// Store minimal session info
			session.setAttribute("USER_ID", student.getId());
			session.setAttribute("USER_ROLE", student.getRole().name());
			session.setAttribute("USER_NAME", student.getName());
			session.setAttribute("USER_EMAIL", student.getEmail());

			// Role-based redirect
			return switch (student.getRole()) {
			case ADMIN -> "redirect:/app/admin/dashboard";
			case TEACHER -> "redirect:/app/teacher/dashboard";
			case STUDENT -> "redirect:/app/student/dashboard";
			};

		} catch (RuntimeException ex) {
			//model.addAttribute("error", ex.getMessage());
			model.addAttribute("error", "Authentication Error");
			
			return "redirect:/app/login";
		}
	}

	// -------- Logout --------
	@PostMapping("/app/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/app/login";
	}
}
