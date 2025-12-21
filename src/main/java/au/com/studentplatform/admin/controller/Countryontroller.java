/**
 * 
 */
package au.com.studentplatform.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import au.com.studentplatform.admin.model.Country;
import au.com.studentplatform.admin.service.CountryService;

/**
 * 
 */
@Controller
@RequestMapping("/app")
public class Countryontroller {
	
	private final CountryService countryService;
	
	public Countryontroller(CountryService countryService) {
		this.countryService = countryService;
	}
	
	// ---------- country ----------
		@GetMapping("/admin/country")
		public String countryList(Model model) {
			model.addAttribute("country", countryService.findAll());
			return "country/list";
		}

		/*
		 * @GetMapping("/country/new") public String newCountryForm(Model model) {
		 * model.addAttribute("country", new Country()); return "country/form"; }
		 */

		@GetMapping("/admin/country/edit/{id}")
		public String editCountryForm(@PathVariable Integer id, Model model) {
			model.addAttribute("country", countryService.findById(id));
			return "country/form";
		}

		@PostMapping("/admin/country/save")
		public String saveCountry(@ModelAttribute Country country, RedirectAttributes ra) {
			countryService.save(country);
			ra.addFlashAttribute("success", "Country saved");
			return "redirect:/app/country";
		}

		@GetMapping("/admin/country/delete/{id}")
		public String deleteCountry(@PathVariable Integer id, RedirectAttributes ra) {
			countryService.deleteById(id);
			ra.addFlashAttribute("success", "Country deleted");
			return "redirect:/app/country";
		}


}
