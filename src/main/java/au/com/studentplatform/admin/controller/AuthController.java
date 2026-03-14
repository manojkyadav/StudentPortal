package au.com.studentplatform.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.studentplatform.admin.model.CreateUserRequest;
import au.com.studentplatform.admin.model.User;
import au.com.studentplatform.admin.model.dto.LoginRequestDto;
import au.com.studentplatform.admin.service.AuthService;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AuthController {

	private final AuthService authService;
	private final ClassRoomService classRoomService;
	private final UserService userService;

	public AuthController(AuthService authService, ClassRoomService classRoomService, UserService userService) {
		this.authService = authService;
		this.classRoomService = classRoomService;
		this.userService = userService;
	}

	@GetMapping("/register")
	public String register(Model model) {

		model.addAttribute("createUserRequest", new CreateUserRequest());
		model.addAttribute("classRoomList", classRoomService.findAll());

		return "register";
	}

	@PostMapping("/register/user")
	public String createUser(@ModelAttribute CreateUserRequest request) {
		userService.createuser(request.getName(), request.getEmail(), request.getPassword(), request.getRole(),
				request.getClassRoomId());
		return "students/dashboard";
	}

	// -------- Show Login Page --------
	@GetMapping("/login")
	public String loginPage(Model model, HttpSession session) {
		model.addAttribute("loginRequest", new LoginRequestDto());
		
		try {
			String email = ""+session.getAttribute("USER_EMAIL");
			
			User user = authService.authenticate(email, "");

			if (user == null) {
				model.addAttribute("error", "Invalid email or password");
				return "login";
			}

			// Store minimal session info
			session.setAttribute("USER_ID", user.getId());
			session.setAttribute("USER_ROLE", user.getRole().name());
			session.setAttribute("USER_NAME", user.getName());
			session.setAttribute("USER_EMAIL", user.getEmail());
			session.setAttribute("USER_CLASS", user.getClassRoomId());

			// Role-based redirect
			return switch (user.getRole()) {
			case ADMIN -> "redirect:/app/admin/dashboard";
			case TEACHER -> "redirect:/app/teacher/dashboard";
			case STUDENT -> "redirect:/app/student/dashboard";
			};

		} catch (RuntimeException ex) {
			// model.addAttribute("error", ex.getMessage());
			model.addAttribute("error", "Authentication Error");

			//return "redirect:/app/login";
		}
		
		return "login";
	}

	// -------- Process Login --------
	@PostMapping("/login")
	public String processLogin(@ModelAttribute("loginRequest") LoginRequestDto loginRequest, HttpSession session,
			Model model) {
		try {
			User user = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

			if (user == null) {
				model.addAttribute("error", "Invalid email or password");
				return "login";
			}

			// Store minimal session info
			session.setAttribute("USER_ID", user.getId());
			session.setAttribute("USER_ROLE", user.getRole().name());
			session.setAttribute("USER_NAME", user.getName());
			session.setAttribute("USER_EMAIL", user.getEmail());
			session.setAttribute("USER_CLASS", user.getClassRoomId());

			// Role-based redirect
			return switch (user.getRole()) {
			case ADMIN -> "redirect:/app/admin/dashboard";
			case TEACHER -> "redirect:/app/teacher/dashboard";
			case STUDENT -> "redirect:/app/student/dashboard";
			};

		} catch (RuntimeException ex) {
			// model.addAttribute("error", ex.getMessage());
			model.addAttribute("error", "Authentication Error");

			return "redirect:/app/login";
		}
	}

	// -------- Logout --------
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/app/login";
	}
}
