/**
 * 
 */
package au.com.studentplatform.admin.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.model.ClassRoom;
import au.com.studentplatform.admin.model.Country;
import au.com.studentplatform.admin.service.ClassRoomService;
import au.com.studentplatform.admin.service.CountryService;

/**
 * 
 */
@Controller
@RequestMapping("/app")
public class ClassesController {

	private final CountryService countryService;
	private final ClassRoomService classroomService;
	
	public ClassesController(CountryService countryService, ClassRoomService classroomService) {
		this.countryService = countryService;
		this.classroomService = classroomService;
	}
	
	// ---------- ClassRoom ----------
		@GetMapping("/admin/classes")
		public String classes(Model model) {
			model.addAttribute("classes", classroomService.findAll());

			List<Country> countryList = countryService.findAll();
			model.addAttribute("countryList", countryList);
			model.addAttribute("classroom", new ClassRoom());

			return "admin/classes";
		}

		@GetMapping("/admin/classes/edit/{id}")
		public String editClassRoomForm(@PathVariable Integer id, Model model) {

			ClassRoom classes = classroomService.findById(id);
			Country country = countryService.findById(classes.getCountry().getId());
			model.addAttribute("countryList", country);

			model.addAttribute("classroom", classes);
			return "admin/classes";
		}

		@PostMapping("/admin/classes/save")
		public String saveClassRoom(@ModelAttribute ClassRoom classRoom, RedirectAttributes ra) {
			classroomService.save(classRoom);
			ra.addFlashAttribute("success", "Class details saved");
			return "redirect:/app/classes";
		}

		@GetMapping("/admin/classes/delete/{id}")
		public String deleteClassRoom(@PathVariable Integer id, RedirectAttributes ra) {
			classroomService.deleteById(id);
			ra.addFlashAttribute("success", "ClassRoom deleted");
			return "redirect:/app/classes";
		}
}
