package com.example.demo.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

	@GetMapping("/signup")
	public String getSignUp(Model model) {
		return "signup.html";
	}
	
	@PostMapping("/signup")
	public String postSignUp(Model model) {
		return "redirect:/login.html";
	}
}
