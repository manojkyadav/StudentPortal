package au.com.studentplatform.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.common.GenericQuestionGenerator;
import au.com.studentplatform.admin.common.GenericTopicGenerator;
import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.dto.DashboardStatsDTO;
import au.com.studentplatform.admin.model.dto.GenericGeneratorDTO;
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
	private final GenericQuestionGenerator genericQuestionGenerator;
	private final GenericTopicGenerator genericTopicGenerator;
	private final ClassRoomService classroomService;

	public AdminController(StudentService studentService, ClassRoomService classRoomService,
			QuestionService questionService, SubjectService subjectService, TopicService topicService,
			DashboardService dashboardService, GenericQuestionGenerator genericQuestionGenerator,
			GenericTopicGenerator genericTopicGenerator, ClassRoomService classroomService) {
		this.studentService = studentService;
		this.classRoomService = classRoomService;
		this.questionService = questionService;
		this.subjectService = subjectService;
		this.topicService = topicService;
		this.dashboardService = dashboardService;
		this.genericQuestionGenerator = genericQuestionGenerator;
		this.genericTopicGenerator = genericTopicGenerator;
		this.classroomService=classroomService;
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
	
	@GetMapping("/admin/topic/topicgenerator")
	public String createNewTopics(@RequestParam(required = false) Integer classId, Model model) {
		
		try {
			model.addAttribute("genericGeneratorDTO", new GenericGeneratorDTO());
			
			List<ClassRoom> classroomList = classroomService.findAll();
			model.addAttribute("classroomList", classroomList);

			if (classId != null) {
				model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));

			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "admin/topicgenerator";
	}
	
	@GetMapping("/admin/questions/genericQuestionGenerator")
	public String genericNewQuestion(@RequestParam(required = false) Integer classId, @RequestParam(required = false) Integer subjectId, @RequestParam(required = false) Integer topicId, Model model) {
		
		try {
			model.addAttribute("genericGeneratorDTO", new GenericGeneratorDTO());
			
			List<ClassRoom> classroomList = classroomService.findAll();
			model.addAttribute("classroomList", classroomList);

			if (classId != null) {
				model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));
			}
			
			if (subjectId != null) {
				model.addAttribute("topicList", topicService.getTopicBySubjectId(subjectId));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "admin/questiongenerator";
	}
	
	
	@PostMapping("/admin/topic/topicgenerator")
	public String createTopics(@ModelAttribute("question") GenericGeneratorDTO GenericGeneratorDTO,
            RedirectAttributes ra) {
		
		try {
			genericTopicGenerator.generateTopics(GenericGeneratorDTO.getClassId(), GenericGeneratorDTO.getSubjectId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/app/admin/topic/topicgenerator";
	}
	
	@PostMapping("/admin/questions/genericQuestionGenerator")
	public String GenericQuestionGenerator(@ModelAttribute("question") GenericGeneratorDTO GenericGeneratorDTO,
            RedirectAttributes ra) {
		
		try {
			genericQuestionGenerator.generateQuestions(GenericGeneratorDTO.getClassId(), GenericGeneratorDTO.getSubjectId(), GenericGeneratorDTO.getTopicId(), 100, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/app/admin/questions/genericQuestionGenerator";
	}
	
}
