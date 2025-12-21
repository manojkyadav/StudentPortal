package au.com.studentplatform.admin.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.model.TestSession;
import au.com.studentplatform.admin.model.TestStatus;
import au.com.studentplatform.admin.model.UserAnswer;
import au.com.studentplatform.admin.model.dto.QuestionResultDTO;
import au.com.studentplatform.admin.repository.TestSessionRepository;
import au.com.studentplatform.admin.service.OptionService;
import au.com.studentplatform.admin.service.QuestionService;
import au.com.studentplatform.admin.service.TestService;
import au.com.studentplatform.admin.service.UserAnswerService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/app")
public class QuizController {

	private TestService testService;
	private UserAnswerService userAnswerService;
	private TestSessionRepository testSessionRepository;
	private QuestionService questionService;
	private OptionService optionService;

	public QuizController(TestService testService, UserAnswerService userAnswerService,
			TestSessionRepository testSessionRepository, QuestionService questionService, OptionService optionService) {

		this.testService = testService;
		this.userAnswerService = userAnswerService;
		this.testSessionRepository = testSessionRepository;
		this.questionService = questionService;
		this.optionService = optionService;

	}

	@GetMapping("/student/practicequiz")
	public String practicequiz(@RequestParam Integer topicId, @RequestParam String mode, HttpSession session,
			Model model) {

		TestSession testSession = testService.createSession(topicId, mode, ""+session.getAttribute("USER_EMAIL"));
		session.setAttribute("testSessionId", testSession.getId());

		List<Integer> questionIds = questionService.getQuestions(topicId, mode).stream().map(Question::getId).toList();

		session.setAttribute("questionIds", questionIds);
		session.setAttribute("currentIndex", 0);

		return loadQuestion(model, questionIds, 0);// "students/practicequestion";
	}

	private String loadQuestion(Model model, List<Integer> questionIds, int index) {

		Question question = questionService.getQuestionWithOptions(questionIds.get(index));

		model.addAttribute("studentName", "Pihu Yadav");
		model.addAttribute("currentQuestion", index + 1);
		model.addAttribute("totalQuestions", questionIds.size());

		model.addAttribute("question", question.getQuestionTxt());
		model.addAttribute("questionId", question.getId());
		model.addAttribute("options", question.getOptions());

		return "students/practicequestion";
	}
	
	@GetMapping("/student/practicequizresult")
	public String practicequizresult(Model model, HttpSession session) {
		/*Integer sessionId = (Integer) session.getAttribute("testSessionId");
		testService.evaluateTest(sessionId);

		List<UserAnswer> answers = userAnswerService.findBySessionId(sessionId);
		int score = 0;

		for (UserAnswer a : answers) {
			if (a.getIsCorrect()) {
				score += 1;//
				//a.getQuestionId().getMarks();
			}
		}

		TestSession currentSession = testSessionRepository.findById(sessionId).get();
		currentSession.setObtainedMarks(score);
		currentSession.setStatus(TestStatus.SUBMITTED);
		testSessionRepository.save(currentSession);

		model.addAttribute("studentName", "Pihu Yadav");

		model.addAttribute("score", 100.0);
		model.addAttribute("correctCount", 1);
		model.addAttribute("incorrectCount", 0);

		model.addAttribute("question", "What is Newton’s first law?");
		model.addAttribute("userAnswer", "Law of inertia");
		model.addAttribute("correctAnswer", "Law of inertia");
		model.addAttribute("explanation",
				"Newton’s first law states that an object at rest stays at rest unless acted upon by an external force.");
*/
		Integer sessionId = (Integer) session.getAttribute("testSessionId");

	    // 1️⃣ Fetch answers
	    List<UserAnswer> answers = userAnswerService.findBySessionId(sessionId);

	    int correctCount = 0;
	    List<QuestionResultDTO> results = new ArrayList<>();

	    for (UserAnswer ua : answers) {

			//Integer q = ua.getQuestionId(); // ManyToOne
			//Question question = questionService.findById(q);
			//Integer selectedOptionId = ua.getSelectedOptionId(); // ManyToOne
			//Option selected = optionService.findById(selectedOptionId);
	    	Question q = ua.getQuestion();   // ManyToOne
	        Option selected = ua.getOption(); // ManyToOne

	        Option correctOption = q.getOptions()
	                .stream()
	                .filter(Option::isCorrect)
	                .findFirst()
	                .orElse(null);

	      /*  Option correctOption = question.getOptions()
	                .stream()
	                .filter(Option::isCorrect)
	                .findFirst()
	                .orElse(null);
*/
	        boolean isCorrect = ua.getIsCorrect();
	        if (isCorrect) correctCount++;

	        QuestionResultDTO dto = new QuestionResultDTO();
	        dto.setQuestionText(q.getQuestionTxt());
	        dto.setUserAnswer(selected.getText());
	        dto.setCorrectAnswer(correctOption != null ? correctOption.getText() : "");
	        dto.setExplanation(q.getExplanation());
	        dto.setCorrect(isCorrect);

	        results.add(dto);
	    }

	    int total = answers.size();
	    int incorrectCount = total - correctCount;
	    double scorePercent = total == 0 ? 0 : (correctCount * 100.0 / total);

	    // 2️⃣ Update TestSession
	    TestSession testSession = testSessionRepository.findById(sessionId).orElseThrow();
	    testSession.setObtainedMarks(correctCount);
	    testSession.setStatus(TestStatus.SUBMITTED);
	    testSession.setEndTime(LocalDateTime.now());
	    testSession.setTotalMarks(total);
	    testSessionRepository.save(testSession);

	    // 3️⃣ Model attributes
	    model.addAttribute("studentName", session.getAttribute("USER_NAME"));
	    model.addAttribute("score", scorePercent);
	    model.addAttribute("correctCount", correctCount);
	    model.addAttribute("incorrectCount", incorrectCount);
	    model.addAttribute("results", results);
	    
		return "students/practicequestionresult";
	}
	 

	@PostMapping("/student/answer")
	public String saveAnswer(@RequestParam Integer questionId, @RequestParam Integer optionId, HttpSession session) {
		Integer sessionId = (Integer) session.getAttribute("testSessionId");
		testService.saveAnswer(sessionId, questionId, optionId);
		// Move to next question
		Integer currentIndex = (Integer) session.getAttribute("currentIndex");
		session.setAttribute("currentIndex", currentIndex + 1);

		return "redirect:/app/student/next-question";
	}

	@GetMapping("/student/next-question")
	public String nextQuestion(HttpSession session, Model model) {

	    List<Integer> questionIds =
	            (List<Integer>) session.getAttribute("questionIds");
	    Integer currentIndex =
	            (Integer) session.getAttribute("currentIndex");

	    if (currentIndex >= questionIds.size()) {
	        return "redirect:/app/student/practicequizresult";
	    }

	    return loadQuestion(model, questionIds, currentIndex);
	}

}
