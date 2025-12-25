package au.com.studentplatform.admin.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import au.com.studentplatform.admin.model.dto.DashboardStatsDTO;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.DashboardService;
import au.com.studentplatform.admin.service.QuestionService;
import au.com.studentplatform.admin.service.StudentService;
import au.com.studentplatform.admin.service.SubjectService;
import au.com.studentplatform.admin.service.TopicService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class AdminController {

	private final StudentService studentService;
	private final ClassRoomService classRoomService;
	private final SubjectService subjectService;
	private final TopicService topicService;
	private final QuestionService questionService;
	// private final AttemptService attemptService;
	private final DashboardService dashboardService;

	public AdminController(StudentService studentService, ClassRoomService classRoomService,
			QuestionService questionService, SubjectService subjectService, TopicService topicService,
			DashboardService dashboardService) {
		this.studentService = studentService;
		this.classRoomService = classRoomService;
		this.questionService = questionService;
		this.subjectService = subjectService;
		this.topicService = topicService;
		this.dashboardService = dashboardService;
	}

	@GetMapping("/admin/dashboard")
	public String dashboard(Model model, HttpSession session) {

		model.addAttribute("active", "dashboard");

		model.addAttribute("stats", Map.of("classes", 2, "subjects", 5, "topics", 12, "tests", 4));

		model.addAttribute("totalUsers", 150);
		model.addAttribute("activeCourses", 12);
		model.addAttribute("pendingTasks", 7);

		session.setAttribute("userName", "Manoj Yadav");
		session.setAttribute("role", "Admin");

		return "admin/dashboard";
	}

	@GetMapping("/student/dashboard")
	public String studentDashboard(Model model, HttpSession session) {
		
		String email = (String) session.getAttribute("USER_EMAIL");
		String name = (String) session.getAttribute("USER_NAME");

		DashboardStatsDTO stats = dashboardService.getStats(email);

		model.addAttribute("stats", stats);
		model.addAttribute("topicPerformance", dashboardService.getTopicPerformance(email));
		model.addAttribute("recentActivities", dashboardService.getRecentActivities(email));
		
		model.addAttribute("studentName",  (String) session.getAttribute("USER_NAME"));
	    model.addAttribute("activePage", "dashboard");
	    

		return "students/dashboard";
	}
}
