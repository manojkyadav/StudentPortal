package au.com.studentplatform.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.model.Attempt;
import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Country;
import au.com.studentplatform.admin.model.Leaderboard;
import au.com.studentplatform.admin.model.Notification;
import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.QuizManagement;
import au.com.studentplatform.admin.model.QuizManagementQuestion;
import au.com.studentplatform.admin.model.QuizManagementResult;
import au.com.studentplatform.admin.model.Student;
import au.com.studentplatform.admin.model.StudentProgress;
import au.com.studentplatform.admin.model.Subject;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.service.AttemptService;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.CountryService;
import au.com.studentplatform.admin.service.LeaderboardService;
import au.com.studentplatform.admin.service.NotificationService;
import au.com.studentplatform.admin.service.OptionService;
import au.com.studentplatform.admin.service.QuestionService;
import au.com.studentplatform.admin.service.QuizManagementQuestionService;
import au.com.studentplatform.admin.service.QuizManagementResultService;
import au.com.studentplatform.admin.service.QuizManagementService;
import au.com.studentplatform.admin.service.StudentProgressService;
import au.com.studentplatform.admin.service.StudentService;
import au.com.studentplatform.admin.service.SubjectService;
import au.com.studentplatform.admin.service.TopicService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class WebController {

	//private final CountryService countryService;
	//private final ClassRoomService classroomService;
	//private final SubjectService subjectService;
	//private final TopicService topicService;
	//private final QuestionService questionService;
	private final OptionService optionService;
	//private final StudentService studentService;

	private final AttemptService attemptService;
	private final QuizManagementService quizService;
	private final QuizManagementQuestionService quizQuestionService;
	private final QuizManagementResultService quizResultService;
	private final StudentProgressService progressService;
	private final LeaderboardService leaderboardService;
	private final NotificationService notificationService;

	public WebController(///CountryService countryService, 
			//ClassRoomService classroomService,
			SubjectService subjectService, TopicService topicService, QuestionService questionService,
			OptionService optionService, StudentService studentService, AttemptService attemptService,
			QuizManagementService quizService, QuizManagementQuestionService quizQuestionService,
			QuizManagementResultService quizResultService, StudentProgressService progressService,
			LeaderboardService leaderboardService, NotificationService notificationService) {
		//this.countryService = countryService;
		//this.classroomService = classroomService;
		//this.subjectService = subjectService;
		//this.topicService = topicService;
		//this.questionService = questionService;
		this.optionService = optionService;
		//this.studentService = studentService;
		this.attemptService = attemptService;
		this.quizService = quizService;
		this.quizQuestionService = quizQuestionService;
		this.quizResultService = quizResultService;
		this.progressService = progressService;
		this.leaderboardService = leaderboardService;
		this.notificationService = notificationService;
	}

/*	

	// ---------- ATTEMPTS ----------
	@GetMapping("/attempts")
	public String attemptsList(Model model) {
		model.addAttribute("attempts", attemptService.findAll());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "attempts/list";
	}

	@GetMapping("/attempts/new")
	public String newAttemptForm(Model model) {
		model.addAttribute("attempt", new Attempt());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "attempts/form";
	}

	@GetMapping("/attempts/edit/{id}")
	public String editAttempt(@PathVariable Integer id, Model model) {
		model.addAttribute("attempt", attemptService.findById(id));
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "attempts/form";
	}

	@PostMapping("/attempts/save")
	public String saveAttempt(@ModelAttribute Attempt attempt, RedirectAttributes ra) {
		attemptService.save(attempt);
		ra.addFlashAttribute("success", "Attempt saved");
		return "redirect:/app/attempts";
	}

	@GetMapping("/attempts/delete/{id}")
	public String deleteAttempt(@PathVariable Integer id, RedirectAttributes ra) {
		attemptService.deleteById(id);
		ra.addFlashAttribute("success", "Attempt deleted");
		return "redirect:/app/attempts";
	}

	// ---------- QUIZZES ----------
	@GetMapping("/quizzes")
	public String quizzesList(Model model) {
		model.addAttribute("quizzes", quizService.findAll());
		return "quizzes/list";
	}

	@GetMapping("/quizzes/new")
	public String newQuizForm(Model model) {
		model.addAttribute("quiz", new QuizManagement());
		return "quizzes/form";
	}

	@GetMapping("/quizzes/edit/{id}")
	public String editQuiz(@PathVariable Integer id, Model model) {
		model.addAttribute("quiz", quizService.findById(id));
		return "quizzes/form";
	}

	@PostMapping("/quizzes/save")
	public String saveQuiz(@ModelAttribute QuizManagement quiz, RedirectAttributes ra) {
		quizService.save(quiz);
		ra.addFlashAttribute("success", "Quiz saved");
		return "redirect:/app/quizzes";
	}

	@GetMapping("/quizzes/delete/{id}")
	public String deleteQuiz(@PathVariable Integer id, RedirectAttributes ra) {
		quizService.deleteById(id);
		ra.addFlashAttribute("success", "Quiz deleted");
		return "redirect:/app/quizzes";
	}

	// ---------- QUIZ QUESTIONS ----------
	@GetMapping("/quiz-questions")
	public String quizQuestionsList(Model model) {
		model.addAttribute("quizQuestions", quizQuestionService.findAll());
		model.addAttribute("quizzes", quizService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "quizQuestions/list";
	}

	@GetMapping("/quiz-questions/new")
	public String newQuizQuestionForm(Model model) {
		model.addAttribute("quizQuestion", new QuizManagementQuestion());
		model.addAttribute("quizzes", quizService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "quizQuestions/form";
	}

	@GetMapping("/quiz-questions/edit/{id}")
	public String editQuizQuestion(@PathVariable Integer id, Model model) {
		model.addAttribute("quizQuestion", quizQuestionService.findById(id));
		model.addAttribute("quizzes", quizService.findAll());
		model.addAttribute("questions", questionService.findAll());
		return "quizQuestions/form";
	}

	@PostMapping("/quiz-questions/save")
	public String saveQuizQuestion(@ModelAttribute QuizManagementQuestion qq, RedirectAttributes ra) {
		quizQuestionService.save(qq);
		ra.addFlashAttribute("success", "Quiz question saved");
		return "redirect:/app/quiz-questions";
	}

	@GetMapping("/quiz-questions/delete/{id}")
	public String deleteQuizQuestion(@PathVariable Integer id, RedirectAttributes ra) {
		quizQuestionService.deleteById(id);
		ra.addFlashAttribute("success", "Quiz question deleted");
		return "redirect:/app/quiz-questions";
	}

	// ---------- QUIZ RESULTS ----------
	@GetMapping("/quiz-results")
	public String quizResultsList(Model model) {
		model.addAttribute("results", quizResultService.findAll());
		return "quizResults/list";
	}

	@GetMapping("/quiz-results/new")
	public String newQuizResultForm(Model model) {
		model.addAttribute("result", new QuizManagementResult());
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("quizzes", quizService.findAll());
		return "quizResults/form";
	}

	@GetMapping("/quiz-results/edit/{id}")
	public String editQuizResult(@PathVariable Integer id, Model model) {
		model.addAttribute("result", quizResultService.findById(id));
		model.addAttribute("students", studentService.findAll());
		model.addAttribute("quizzes", quizService.findAll());
		return "quizResults/form";
	}

	@PostMapping("/quiz-results/save")
	public String saveQuizResult(@ModelAttribute QuizManagementResult result, RedirectAttributes ra) {
		quizResultService.save(result);
		ra.addFlashAttribute("success", "Quiz result saved");
		return "redirect:/app/quiz-results";
	}

	@GetMapping("/quiz-results/delete/{id}")
	public String deleteQuizResult(@PathVariable Integer id, RedirectAttributes ra) {
		quizResultService.deleteById(id);
		ra.addFlashAttribute("success", "Quiz result deleted");
		return "redirect:/app/quiz-results";
	}

	// ---------- STUDENT PROGRESS ----------
	@GetMapping("/progress")
	public String progressList(Model model) {
		model.addAttribute("progressList", progressService.findAll());
		model.addAttribute("students", studentService.findAll());
		return "progress/list";
	}

	@GetMapping("/progress/new")
	public String newProgressForm(Model model) {
		model.addAttribute("progress", new StudentProgress());
		model.addAttribute("students", studentService.findAll());
		return "progress/form";
	}

	@GetMapping("/progress/edit/{id}")
	public String editProgress(@PathVariable Integer id, Model model) {
		model.addAttribute("progress", progressService.findById(id));
		model.addAttribute("students", studentService.findAll());
		return "progress/form";
	}

	@PostMapping("/progress/save")
	public String saveProgress(@ModelAttribute StudentProgress progress, RedirectAttributes ra) {
		progressService.save(progress);
		ra.addFlashAttribute("success", "Progress saved");
		return "redirect:/app/progress";
	}

	@GetMapping("/progress/delete/{id}")
	public String deleteProgress(@PathVariable Integer id, RedirectAttributes ra) {
		progressService.deleteById(id);
		ra.addFlashAttribute("success", "Progress deleted");
		return "redirect:/app/progress";
	}

	// ---------- LEADERBOARD ----------
	@GetMapping("/leaderboard")
	public String leaderboardList(Model model) {
		model.addAttribute("entries", leaderboardService.findAll());
		return "leaderboard/list";
	}

	@GetMapping("/leaderboard/new")
	public String newLeaderboardForm(Model model) {
		model.addAttribute("entry", new Leaderboard());
		model.addAttribute("students", studentService.findAll());
		return "leaderboard/form";
	}

	@GetMapping("/leaderboard/edit/{id}")
	public String editLeaderboard(@PathVariable Integer id, Model model) {
		model.addAttribute("entry", leaderboardService.findById(id));
		model.addAttribute("students", studentService.findAll());
		return "leaderboard/form";
	}

	@PostMapping("/leaderboard/save")
	public String saveLeaderboard(@ModelAttribute Leaderboard entry, RedirectAttributes ra) {
		leaderboardService.save(entry);
		ra.addFlashAttribute("success", "Leaderboard entry saved");
		return "redirect:/app/leaderboard";
	}

	@GetMapping("/leaderboard/delete/{id}")
	public String deleteLeaderboard(@PathVariable Integer id, RedirectAttributes ra) {
		leaderboardService.deleteById(id);
		ra.addFlashAttribute("success", "Leaderboard entry deleted");
		return "redirect:/app/leaderboard";
	}

	// ---------- NOTIFICATIONS ----------
	@GetMapping("/notifications")
	public String notificationsList(Model model) {
		model.addAttribute("notifications", notificationService.findAll());
		model.addAttribute("students", studentService.findAll());
		return "notifications/list";
	}

	@GetMapping("/notifications/new")
	public String newNotificationForm(Model model) {
		model.addAttribute("notification", new Notification());
		model.addAttribute("students", studentService.findAll());
		return "notifications/form";
	}

	@GetMapping("/notifications/edit/{id}")
	public String editNotification(@PathVariable Integer id, Model model) {
		model.addAttribute("notification", notificationService.findById(id));
		model.addAttribute("students", studentService.findAll());
		return "notifications/form";
	}

	@PostMapping("/notifications/save")
	public String saveNotification(@ModelAttribute Notification notification, RedirectAttributes ra) {
		notificationService.save(notification);
		ra.addFlashAttribute("success", "Notification saved");
		return "redirect:/app/notifications";
	}

	@GetMapping("/notifications/delete/{id}")
	public String deleteNotification(@PathVariable Integer id, RedirectAttributes ra) {
		notificationService.deleteById(id);
		ra.addFlashAttribute("success", "Notification deleted");
		return "redirect:/app/notifications";
	}

	*/
	// ---------------------------------------------------//
	

}
