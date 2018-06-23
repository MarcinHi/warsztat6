package pl.coderslab.modul6.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	@PreAuthorize("hasRole(ROLE_ADMIN)")
	public String home() {
		return "home";
	}
}
