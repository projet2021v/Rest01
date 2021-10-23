package fr.diginamic.Rest01.exceptions;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageErrorHandlerController {
		
	@GetMapping("/notFound")
	public String notFoundToHome() {
		return "redirect:/";
	}
}
