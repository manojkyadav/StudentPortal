package au.com.studentplatform.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Option;
import au.com.studentplatform.admin.model.Question;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.QuestionService;
import au.com.studentplatform.admin.service.SubjectService;
import au.com.studentplatform.admin.service.TopicService;

@Controller
@RequestMapping("/app")
public class QuestionController {
	
	private final ClassRoomService classroomService;
	private final SubjectService subjectService;
	private final TopicService topicService;
	private final QuestionService questionService;

	public QuestionController(QuestionService questionService,ClassRoomService classroomService, SubjectService subjectService, TopicService topicService) {
		this.topicService = topicService;
		this.classroomService = classroomService;
		this.subjectService = subjectService;
		this.questionService = questionService;
	}

	// ---------- QUESTIONS ----------
	

		@GetMapping("/admin/questions")
		public String questions(Model model) {
			//model.addAttribute("question", new Question());
			
			Question question = new Question();
			question.getOptions().add(new Option()); // minimum one option
	        model.addAttribute("question", question);
	        
			model.addAttribute("questions", questionService.findAll());

			List<ClassRoom> classroomList = classroomService.findAll();
			model.addAttribute("classroomList", classroomList);
			model.addAttribute("questionTypes", Question.QuestionType.values());
			model.addAttribute("difficultyLevels", Question.DifficultyLevel.values());

			return "admin/questions";
		}
	/*
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
	*/
		@GetMapping("/admin/questions/edit/{id}")
		public String editQuestion(@PathVariable Integer id, @RequestParam(required = false) Integer classId,
				@RequestParam(required = false) Integer subjectId, @RequestParam(required = false) Integer topicId,
				Model model) {
			model.addAttribute("question", questionService.findById(id));
			// model.addAttribute("topics", topicService.findAll());

			// model.addAttribute("question", new Question());
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

		@PostMapping("/admin/questions/save")
		public String saveQuestion(@ModelAttribute("question") Question question,
	            RedirectAttributes ra) {

			questionService.save(question);
			ra.addFlashAttribute("success", "Question saved successfully");
			
			return "redirect:/app/admin/questions";
		}

		@GetMapping("/admin/questions/delete/{id}")
		public String deleteQuestion(@PathVariable Integer id, RedirectAttributes ra) {
			questionService.deleteById(id);
			ra.addFlashAttribute("success", "Question deleted");
			return "redirect:/app/adminquestions";
		}
}
