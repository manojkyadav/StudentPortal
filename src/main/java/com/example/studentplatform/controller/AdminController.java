package com.example.studentplatform.controller;

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

import com.example.studentplatform.config.SecurityConfig;
import com.example.studentplatform.model.ClassRoom;
import com.example.studentplatform.model.Question;
import com.example.studentplatform.model.Student;
import com.example.studentplatform.model.Subject;
import com.example.studentplatform.model.Topic;
import com.example.studentplatform.service.AttemptService;
import com.example.studentplatform.service.ClassRoomService;
import com.example.studentplatform.service.QuestionService;
import com.example.studentplatform.service.StudentService;
import com.example.studentplatform.service.SubjectService;
import com.example.studentplatform.service.TopicService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final StudentService studentService;
	private final ClassRoomService classRoomService;
	private final SubjectService subjectService;
	private final TopicService topicService;
	private final QuestionService questionService;
	// private final AttemptService attemptService;

	public AdminController(StudentService studentService, ClassRoomService classRoomService,
			QuestionService questionService, AttemptService attemptService, SecurityConfig securityConfig,
			SubjectService subjectService, TopicService topicService) {
		this.studentService = studentService;
		this.classRoomService = classRoomService;
		this.questionService = questionService;
		this.subjectService = subjectService;
		this.topicService = topicService;
	}
/*
	@GetMapping("/dashboard")
	public String dashboard(Model m) {
		m.addAttribute("students", studentService.list());
		m.addAttribute("classes", classRoomService.list());
		m.addAttribute("questions", questionService.list());
		return "admin/dashboard";
	}

	@GetMapping("/classes")
	public String getClasses(Model model) {

		List<ClassRoom> classList = classRoomService.list();
		model.addAttribute("classList", classList);

		return "admin/classes";
	}

	@PostMapping("/addclasses")
	public String createClass(@RequestParam String name, @RequestParam(required = false) String description,
			Model model) {
		ClassRoom c = new ClassRoom();
		c.setClassName(name);
		c.setDescription(description);
		classRoomService.add(c);
		// List<ClassRoom> classList = classRepo.findAll();
		// model.addAttribute("classList", classList);
		model.addAttribute("message", "Class Added successfully");

		return "redirect:/admin/classes";
	}

	@PostMapping("/editclasses")
	public String editClass(@RequestParam String classid, @RequestParam String name,
			@RequestParam(required = false) String description, Model model) {
		ClassRoom c = new ClassRoom();
		c.setId(classid);
		c.setClassName(name);
		c.setDescription(description);
		classRoomService.edit(c);
		// List<ClassRoom> classList = classRepo.findAll();
		// model.addAttribute("classList", classList);
		model.addAttribute("message", "Class edited successfully");

		return "redirect:/admin/classes";
	}

	@GetMapping("/deleteclasses/{classid}")
	public String deleteClass(@PathVariable String classid, Model model) {
		classRoomService.delete(classid);
		model.addAttribute("message", "Class Deleted successfully");

		return "redirect:/admin/classes";
	}

	// Subject details implementation

	@GetMapping("/subjects")
	public String getSubjects(Model model) {

		List<Subject> subjectList = subjectService.list();
		model.addAttribute("subjectList", subjectList);

		List<ClassRoom> classList = classRoomService.list();
		model.addAttribute("classList", classList);

		return "admin/subjects";
	}

	@PostMapping("/addsubjects")
	public String createSubject(@RequestParam String className, @RequestParam String subjectName,
			@RequestParam(required = false) String description, Model model) {
		Subject c = new Subject();
		c.setClassName(className);
		c.setSubjectName(subjectName);
		c.setDescription(description);
		subjectService.add(c);
		model.addAttribute("message", "Subject Added successfully");

		return "redirect:/admin/subjects";
	}

	@PostMapping("/editsubjects")
	public String editSubjects(@RequestParam String className, @RequestParam String subjectName,
			@RequestParam(required = false) String description, Model model) {
		Subject c = new Subject();
		// c.setId(id);
		c.setClassName(className);
		c.setSubjectName(subjectName);
		c.setDescription(description);
		subjectService.edit(c);
		// List<ClassRoom> classList = classRepo.findAll();
		// model.addAttribute("classList", classList);
		model.addAttribute("message", "Subject edited successfully");

		return "redirect:/admin/subjects";
	}

	@GetMapping("/deletesubjects/{subjectid}")
	public String deleteSubject(@PathVariable String subjectid, Model model) {
		subjectService.delete(subjectid);
		model.addAttribute("message", "Subject Deleted successfully");

		return "redirect:/admin/subjects";
	}

	// Topics details implementation

	@GetMapping("/topics")
	public String getTopics(Model model) {

		List<Subject> subjectList = subjectService.list();
		model.addAttribute("subjectList", subjectList);

		List<ClassRoom> classList = classRoomService.list();
		model.addAttribute("classList", classList);

		List<Topic> topicList = topicService.list();
		model.addAttribute("topicList", topicList);

		return "admin/topics";
	}

	@PostMapping("/addtopics")
	public String createTopics(@RequestParam String className, @RequestParam String subjectName,
			@RequestParam(required = true) String topicName, Model model) {
		Topic c = new Topic();
		c.setTopicName(topicName);
		c.setClassName(className);
		c.setSubjectName(subjectName);

		topicService.add(c);
		model.addAttribute("message", "Topic Added successfully");

		return "redirect:/admin/topics";
	}

	@PostMapping("/edittopic")
	public String editTopics(@RequestParam String topicId, @RequestParam String subjectId,
			@RequestParam(required = false) String topicName, Model model) {
		Topic c = new Topic();
		c.setTopicName(topicName);
		c.setId(topicId);
		c.setSubjectName(subjectId);

		topicService.edit(c);
		model.addAttribute("message", "Topic edited successfully");

		return "redirect:/admin/topics";
	}

	@GetMapping("/deletetopic/{topicid}")
	public String deleteTopic(@PathVariable String topicid, Model model) {
		topicService.delete(topicid);
		model.addAttribute("message", "Topic Deleted successfully");

		return "redirect:/admin/topics";
	}

	@GetMapping("/question")
	public String question(@RequestParam(required = false) String classIName,
			@RequestParam(required = false) String subjectName, @RequestParam(required = false) String topicName, Model model) {

		
		List<ClassRoom> classList = classRoomService.list();
		model.addAttribute("classList", classList);
		
		List<Subject> subjectList = subjectService.list();
		model.addAttribute("subjectList", subjectList);


		List<Topic> topicList = topicService.list();
		model.addAttribute("topicList", topicList);

		Question q = new Question(); 
		
		if(classIName != null)
		  q.setClassName(classIName);
		if(subjectName != null)
		  q.setSubjectName(subjectName);
		if(topicName != null)
		  q.setTopicName(topicName);
  
		
		Question question = new Question();
		// Add 4 empty options initially
		question.setOptions(List.of(new Options(), new Options(), new Options(), new Options()));
		model.addAttribute("question", question);

		return "admin/addquestions";
	}
	
	@GetMapping("/questions")
	public String questions(@RequestParam(required = false) String classIName,
			@RequestParam(required = false) String subjectName, @RequestParam(required = false) String topicName, Model model) {

		List<Subject> subjectList = subjectService.list();
		model.addAttribute("subjectList", subjectList);

		List<ClassRoom> classList = classRoomService.list();
		model.addAttribute("classList", classList);

		List<Topic> topicList = topicService.list();
		model.addAttribute("topicList", topicList);

		Question q = new Question(); 
		
		if(classIName != null)
		  q.setClassName(classIName);
		if(subjectName != null)
		  q.setSubjectName(subjectName);
		if(topicName != null)
		  q.setTopicName(topicName);
  
		List<Question> questionList = questionService.getClassSubjectQuestions(q);
  
		model.addAttribute("questionList", questionList);
		 
		Question question = new Question();
		// Add 4 empty options initially
		question.setOptions(List.of(new Options(), new Options(), new Options(), new Options()));
		model.addAttribute("question", question);

		return "admin/questions";
	}

	@PostMapping("/addquestion")
	public String createQuestion(@ModelAttribute("question") Question question)
	{
		questionService.add(question);

		return "redirect:/admin/questions";
	}

	@GetMapping("/deletequestion/{questionId}")
	public String deletEquestion(@PathVariable String questionId, Model model) {
		questionService.delete(questionId);
		model.addAttribute("message", "Question Deleted successfully");

		return "redirect:/admin/questions";
	}

	@PostMapping("/students/create")
	public String createStudent(@RequestParam String name, @RequestParam String email, @RequestParam String password,
			@RequestParam(required = false) String classId) {
		Student s = new Student();
		s.setName(name);
		s.setEmail(email);
		s.setClassId(classId);
		s.setPasswordHash(password);
		// studentRepo.save(s);
		return "redirect:/admin/dashboard";
	}*/
}
