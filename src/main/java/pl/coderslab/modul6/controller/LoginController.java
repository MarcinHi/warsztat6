package pl.coderslab.modul6.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import pl.coderslab.modul6.dao.UserDao;
import pl.coderslab.modul6.entity.User;

@Controller
public class LoginController {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/login")
	public String login(Model model) {
		if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
			return "redirect:/home";
		}
		model.addAttribute(new User());
		return "login";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute User user, HttpServletRequest request) { 
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String rawPassword = user.getPassword();
		user.setPassword(passwordEncoder.encode(rawPassword));
		userDao.save(user);

		/**
		 * Automatyczne logowanie po rejestracji
		 */
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.getUserName(),
				rawPassword);
		authToken.setDetails(new WebAuthenticationDetails(request));

		Authentication authentication = authenticationManager.authenticate(authToken);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/home";
	}
}
