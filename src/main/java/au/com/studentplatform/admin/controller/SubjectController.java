package au.com.studentplatform.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Subject;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.CountryService;
import au.com.studentplatform.admin.service.SubjectService;

@Controller
@RequestMapping("/app")
public class SubjectController {

	private final CountryService countryService;
	private final ClassRoomService classroomService;
	private final SubjectService subjectService;

	public SubjectController(CountryService countryService, ClassRoomService classroomService,
			SubjectService subjectService) {
		this.countryService = countryService;
		this.classroomService = classroomService;
		this.subjectService = subjectService;
	}

	// ---------- subjects ----------
	@GetMapping("/admin/subjects/classid/{classId}")
	@ResponseBody
	public List<Subject> getSubjectsByClass(@PathVariable Integer classId) {
		// model.addAttribute("subjectList",
		// subjectService.getSubjectsByClassId(classId));

		return subjectService.getSubjectsByClassId(classId);
	}
	@GetMapping("/student/subjects/classid/{classId}")
	//@ResponseBody
	public String getSubjectsByClassId(@PathVariable Integer classId, Model model) {
		// model.addAttribute("subjectList",
		// subjectService.getSubjectsByClassId(classId));

		List<Subject>  subjects = subjectService.getSubjectsByClassId(classId);
		model.addAttribute("subjectList",subjects);
		
		return "students/subjects";
	}

	@GetMapping("/admin/subjects")
	public String subjectsList(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		model.addAttribute("subject", new Subject());
		model.addAttribute("classRoomList", classroomService.findAll());
		return "admin/subjects";
	}

	@GetMapping("/admin/subjects/edit/{id}")
	public String editSubjectForm(@PathVariable Integer id, Model model) {
		Subject subject = subjectService.findById(id);
		model.addAttribute("subject", subject);

		ClassRoom classroom = classroomService.findById(subject.getClassRoom().getId());
		model.addAttribute("classRoomList", classroom);

		return "subject/form";
	}

	@PostMapping("/admin/subjects/save")
	public String savesubject(@ModelAttribute Subject subject, RedirectAttributes ra) {
		subjectService.save(subject);
		ra.addFlashAttribute("success", "subject saved");
		return "redirect:/app/subjects";
	}

	@GetMapping("/admin/subjects/delete/{id}")
	public String deletesubject(@PathVariable Integer id, RedirectAttributes ra) {
		subjectService.deleteById(id);
		ra.addFlashAttribute("success", "subject deleted");
		return "redirect:/app/subjects";
	}

	// student dashboard -------
	@GetMapping("/student/subjects")
		public String studentSubjects(Model model) {
		model.addAttribute("subjects", subjectService.findAll());
		return "students/subjects";
	}
}
