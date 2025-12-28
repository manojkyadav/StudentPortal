package au.com.studentplatform.admin.controller;

import java.util.List;

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

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Topic;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.SubjectService;
import au.com.studentplatform.admin.service.TopicService;
import jakarta.servlet.http.HttpSession;
import au.com.studentplatform.admin.common.GenericTopicGenerator;

@Controller
@RequestMapping("/app")
public class TopicController {

	private final ClassRoomService classroomService;
	private final SubjectService subjectService;
	private final TopicService topicService;
	private final GenericTopicGenerator genericTopicGenerator;

	public TopicController(ClassRoomService classroomService, SubjectService subjectService,
			TopicService topicService, GenericTopicGenerator genericTopicGenerator) {
		this.topicService = topicService;
		this.classroomService = classroomService;
		this.subjectService = subjectService;
		this.genericTopicGenerator=genericTopicGenerator;
	}

	// ---------- TOPICS ----------
	/*
	 * @GetMapping("/topics") public String topicsList(Model model) {
	 * model.addAttribute("topics", topicService.findAll()); return "topics/list"; }
	 */
	@GetMapping("/admin/topics")
	public String topics(@RequestParam(required = false) Integer classId, Model model) {
		model.addAttribute("topics", topicService.findAll());

		model.addAttribute("topic", new Topic());
		model.addAttribute("selectedClassId", classId);

		List<ClassRoom> classroomList = classroomService.findAll();
		model.addAttribute("classroomList", classroomList);

		if (classId != null) {
			model.addAttribute("subjectList", subjectService.getSubjectsByClassId(classId));

		}
		return "admin/topics";
	}

	@GetMapping("/admin/topics/edit/{id}")
	public String editTopicForm(@PathVariable Integer id, Model model) {
		model.addAttribute("topic", topicService.findById(id));
		return "topics/form";
	}

	@PostMapping("/admin/topics/save")
	public String saveTopic(@ModelAttribute Topic topic, RedirectAttributes ra) {
		topicService.save(topic);
		ra.addFlashAttribute("success", "Topic saved");
		return "redirect:/app/topics";
	}

	@GetMapping("/admin/topics/delete/{id}")
	public String deleteTopic(@PathVariable Integer id, RedirectAttributes ra) {
		topicService.deleteById(id);
		ra.addFlashAttribute("success", "Topic deleted");
		return "redirect:/app/topics";
	}

	@GetMapping("/admin/topics/subjectId/{subjectId}")
	@ResponseBody
	public List<Topic> getTopicBySubjectId(@PathVariable Integer subjectId, Model model) {
		// model.addAttribute("topicList", topicService.getTopicBySubjectId(subjectId));
		return topicService.getTopicBySubjectId(subjectId);
	}

	// student dashboard -------
	@GetMapping("/student/topics")
	public String studentTopics(HttpSession session, Model model) {
		
		
		model.addAttribute("topics", topicService.findAll());
		
		return "students/topics";
	}
	
	@GetMapping("/student/topics/subjectId/{subjectId}")
	//@ResponseBody
	public String getSubjectTopicById(@PathVariable Integer subjectId, Model model , HttpSession session) {
			//model.addAttribute("topicList", topicService.getTopicBySubjectId(subjectId));
			List<Topic> topics =  topicService.getTopicBySubjectId(subjectId);
			
			model.addAttribute("topicList", topics);
			model.addAttribute("studentName",  (String) session.getAttribute("USER_NAME"));
		    model.addAttribute("activePage", "subjects");
		
		return "students/topics";
	}
	
	@GetMapping("/admin/topics/subjectId/generictopic")
	//@ResponseBody
	public String generateTopics(@RequestParam   int classId, @RequestParam  int subjectId) {
			
			try {
				genericTopicGenerator.generateTopics(classId, subjectId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return "success";
		}

}
