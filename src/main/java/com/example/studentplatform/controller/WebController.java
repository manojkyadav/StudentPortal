package com.example.studentplatform.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.studentplatform.model.Attempt;
import com.example.studentplatform.model.ClassRoom;
import com.example.studentplatform.model.Country;
import com.example.studentplatform.model.Leaderboard;
import com.example.studentplatform.model.Notification;
import com.example.studentplatform.model.Question;
import com.example.studentplatform.model.QuestionOption;
import com.example.studentplatform.model.QuizManagement;
import com.example.studentplatform.model.QuizManagementQuestion;
import com.example.studentplatform.model.QuizManagementResult;
import com.example.studentplatform.model.Student;
import com.example.studentplatform.model.StudentProgress;
import com.example.studentplatform.model.Subject;
import com.example.studentplatform.model.Topic;
import com.example.studentplatform.service.AttemptService;
import com.example.studentplatform.service.ClassRoomService;
import com.example.studentplatform.service.CountryService;
import com.example.studentplatform.service.LeaderboardService;
import com.example.studentplatform.service.NotificationService;
import com.example.studentplatform.service.OptionService;
import com.example.studentplatform.service.QuestionService;
import com.example.studentplatform.service.QuizManagementQuestionService;
import com.example.studentplatform.service.QuizManagementResultService;
import com.example.studentplatform.service.QuizManagementService;
import com.example.studentplatform.service.StudentProgressService;
import com.example.studentplatform.service.StudentService;
import com.example.studentplatform.service.SubjectService;
import com.example.studentplatform.service.TopicService;

@Controller
@RequestMapping("/app")
public class WebController {


	private final CountryService countryService;
	private final ClassRoomService classroomService;
	private final SubjectService subjectService;
	private final TopicService topicService;
    private final QuestionService questionService;
    private final OptionService optionService;
    private final StudentService studentService;
    
    private final AttemptService attemptService;
    private final QuizManagementService quizService;
    private final QuizManagementQuestionService quizQuestionService;
    private final QuizManagementResultService quizResultService;
    private final StudentProgressService progressService;
    private final LeaderboardService leaderboardService;
    private final NotificationService notificationService;

    public WebController(CountryService countryService,
    		 ClassRoomService classroomService,
    		 SubjectService subjectService,
    		 TopicService topicService,
             QuestionService questionService,
             OptionService optionService,
             StudentService studentService,
             AttemptService attemptService,
             QuizManagementService quizService,
             QuizManagementQuestionService quizQuestionService,
             QuizManagementResultService quizResultService,
             StudentProgressService progressService,
             LeaderboardService leaderboardService,
             NotificationService notificationService) {
    	this.countryService = countryService;
    	this.classroomService = classroomService;
    	this.subjectService = subjectService;
        this.topicService = topicService;
        this.questionService = questionService;
        this.optionService = optionService;
        this.studentService = studentService;
        this.attemptService = attemptService;
        this.quizService = quizService;
        this.quizQuestionService = quizQuestionService;
        this.quizResultService = quizResultService;
        this.progressService = progressService;
        this.leaderboardService = leaderboardService;
        this.notificationService = notificationService;
    }

 // ---------- country ----------
    @GetMapping("/country")
    public String countryList(Model model) {
        model.addAttribute("country", countryService.findAll());
        return "country/list";
    }

    @GetMapping("/country/new")
    public String newCountryForm(Model model) {
        model.addAttribute("country", new Country());
        return "country/form";
    }

    @GetMapping("/country/edit/{id}")
    public String editCountryForm(@PathVariable Integer id, Model model) {
        model.addAttribute("country", countryService.findById(id));
        return "country/form";
    }

    @PostMapping("/country/save")
    public String saveCountry(@ModelAttribute Country country, RedirectAttributes ra) {
    	countryService.save(country);
        ra.addFlashAttribute("success", "Country saved");
        return "redirect:/app/country";
    }

    @GetMapping("/country/delete/{id}")
    public String deleteCountry(@PathVariable Integer id, RedirectAttributes ra) {
    	countryService.deleteById(id);
        ra.addFlashAttribute("success", "Country deleted");
        return "redirect:/app/country";
    }
    
 // ---------- ClassRoom ----------
    @GetMapping("/classroom")
    public String classRoomList(Model model) {
        model.addAttribute("classroom", classroomService.findAll());
        return "classroom/list";
    }

    @GetMapping("/classroom/new")
    public String newClassroomForm(Model model) {
    	List<Country> countryList =countryService.findAll();
    	model.addAttribute("countryList", countryList);
    	
        model.addAttribute("classroom", new ClassRoom());
        return "classroom/form";
    }

    @GetMapping("/classroom/edit/{id}")
    public String editClassRoomForm(@PathVariable Integer id, Model model) {
    	
    	ClassRoom classroom = classroomService.findById(id);
    	Country country =countryService.findById(classroom.getCountry().getId());
    	model.addAttribute("countryList", country);
    	
        model.addAttribute("classroom", classroom);
        return "classroom/form";
    }

    @PostMapping("/classroom/save")
    public String saveClassRoom(@ModelAttribute ClassRoom ClassRoom, RedirectAttributes ra) {
        classroomService.save(ClassRoom);
        ra.addFlashAttribute("success", "ClassRoom saved");
        return "redirect:/app/classroom";
    }

    @GetMapping("/classroom/delete/{id}")
    public String deleteClassRoom(@PathVariable Integer id, RedirectAttributes ra) {
        classroomService.deleteById(id);
        ra.addFlashAttribute("success", "ClassRoom deleted");
        return "redirect:/app/classroom";
    }
    
 // ---------- subjects ----------
    @GetMapping("/subjects")
    public String subjectsList(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subject/list";
    }

    @GetMapping("/subjects/new")
    public String newsubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        model.addAttribute("classRoomList", classroomService.findAll());
        return "subject/form";
    }

    @GetMapping("/subjects/edit/{id}")
    public String editSubjectForm(@PathVariable Integer id, Model model) {
    	Subject subject = subjectService.findById(id);
        model.addAttribute("subject", subject);
        
        ClassRoom classroom =classroomService.findById(subject.getClassRoom().getId());
    	model.addAttribute("classRoomList", classroom);
    	
        return "subject/form";
    }

    @PostMapping("/subjects/save")
    public String savesubject(@ModelAttribute Subject subject, RedirectAttributes ra) {
        subjectService.save(subject);
        ra.addFlashAttribute("success", "subject saved");
        return "redirect:/app/subjects";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deletesubject(@PathVariable Integer id, RedirectAttributes ra) {
        subjectService.deleteById(id);
        ra.addFlashAttribute("success", "subject deleted");
        return "redirect:/app/subjects";
    }
    // ---------- TOPICS ----------
    @GetMapping("/topics")
    public String topicsList(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return "topics/list";
    }

    @GetMapping("/topics/new")
    public String newTopicForm(@RequestParam(required = false) Integer classId, Model model) {
        model.addAttribute("topic", new Topic());
        model.addAttribute("selectedClassId", classId);
        
        List<ClassRoom> classroomList =classroomService.findAll();
        model.addAttribute("classroomList", classroomList);
        
        if (classId != null) {
        	model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));
        	
        }
        
        return "topics/form";
    }

    @GetMapping("/topics/edit/{id}")
    public String editTopicForm(@PathVariable Integer id, Model model) {
        model.addAttribute("topic", topicService.findById(id));
        return "topics/form";
    }

    @PostMapping("/topics/save")
    public String saveTopic(@ModelAttribute Topic topic, RedirectAttributes ra) {
        topicService.save(topic);
        ra.addFlashAttribute("success", "Topic saved");
        return "redirect:/app/topics";
    }

    @GetMapping("/topics/delete/{id}")
    public String deleteTopic(@PathVariable Integer id, RedirectAttributes ra) {
        topicService.deleteById(id);
        ra.addFlashAttribute("success", "Topic deleted");
        return "redirect:/app/topics";
    }

    // ---------- QUESTIONS ----------
    @GetMapping("/questions")
    public String questionsList(Model model) {
        model.addAttribute("questions", questionService.findAll());
        return "questions/list";
    }

	@GetMapping("/questions/new")
	public String newQuestionForm(@RequestParam(required = false) Integer classId,
			@RequestParam(required = false) Integer subjectId, Model model) {
		model.addAttribute("question", new Question());
		// model.addAttribute("topics", topicService.findAll());

		model.addAttribute("selectedClassId", classId);
		model.addAttribute("selectedSubjectId", subjectId);

		List<ClassRoom> classroomList = classroomService.findAll();
		model.addAttribute("classroomList", classroomList);

		if (classId != null) {
			model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));

		}
		if (subjectId != null) {
			model.addAttribute("topicList", topicService.getTopicBySubjectId(subjectId));

		}

		return "questions/form";
	}

    @GetMapping("/questions/edit/{id}")
	public String editQuestion(@PathVariable Integer id, @RequestParam(required = false) Integer classId,
			@RequestParam(required = false) Integer subjectId, @RequestParam(required = false) Integer topicId,
			Model model) {
		model.addAttribute("question", questionService.findById(id));
		//model.addAttribute("topics", topicService.findAll());

		//model.addAttribute("question", new Question());
		// model.addAttribute("topics", topicService.findAll());

		model.addAttribute("selectedClassId", classId);
		model.addAttribute("selectedSubjectId", subjectId);
		model.addAttribute("selectedTopicId", topicId);

		List<ClassRoom> classroomList = classroomService.findAll();
		model.addAttribute("classroomList", classroomList);

		if (classId != null) {
			model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));

		}
		if (subjectId != null) {
			model.addAttribute("topicList", topicService.getTopicBySubjectId(subjectId));

		}
        return "questions/form";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(@ModelAttribute Question question,
            @RequestParam(required = false) List<String> optionText,
            @RequestParam(required = false) List<Integer> correctOptionIndex, RedirectAttributes ra) {

        // Save question first
    	question.setCreatedAt(LocalDateTime.now());
    	Question savedQuestion = questionService.save(question);
        ra.addFlashAttribute("success", "Question saved");

        // Handle options only for multiple-choice questions
        //if (question.getQuestionType() == Question.QuestionType.MULTIPLE_CHOICE && optionText != null) {
        if (question.getQuestionType() == Question.QuestionType.MULTIPLE_CHOICE && question.getOptions() != null) {
            List<QuestionOption> options = question.getOptions();//new ArrayList<>();
            for (int i = 0; i < options.size(); i++) {
				/*
				 * QuestionOption opt = new QuestionOption();
				 * opt.setQuestionId(savedQuestion.getId());
				 * opt.setOptionText(optionText.get(i)); opt.setIsCorrect(correctOptionIndex !=
				 * null && correctOptionIndex.contains(i)); options.add(opt);
				 */
            	options.get(i).setQuestionId(savedQuestion.getId());
            }
            optionService.saveAll(options);
        }
        return "redirect:/app/questions";
    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id, RedirectAttributes ra) {
        questionService.deleteById(id);
        ra.addFlashAttribute("success", "Question deleted");
        return "redirect:/app/questions";
    }

    // ---------- OPTIONS ----------
    @GetMapping("/options")
    public String optionsList(Model model) {
        model.addAttribute("options", optionService.findAll());
        return "options/list";
    }

    @GetMapping("/options/new")
    public String newOptionForm(Model model) {
        model.addAttribute("optionEntity", new QuestionOption());
        model.addAttribute("questions", questionService.findAll());
        return "options/form";
    }

    @GetMapping("/options/edit/{id}")
    public String editOption(@PathVariable Integer id, Model model) {
        model.addAttribute("optionEntity", optionService.findById(id));
        model.addAttribute("questions", questionService.findAll());
        return "options/form";
    }

    @PostMapping("/options/save")
    public String saveOption(@ModelAttribute("optionEntity") QuestionOption option, RedirectAttributes ra) {
        optionService.save(option);
        ra.addFlashAttribute("success", "Option saved");
        return "redirect:/app/options";
    }

    @GetMapping("/options/delete/{id}")
    public String deleteOption(@PathVariable Integer id, RedirectAttributes ra) {
        optionService.deleteById(id);
        ra.addFlashAttribute("success", "Option deleted");
        return "redirect:/app/options";
    }

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
    }

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
}
