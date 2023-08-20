package com.zyonson.example.paidvacation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/clogin")
	public String login() {
		return "/login/login";
	}
	
	@PostMapping("/clogin")
    String postLogin() {
        return "redirect:/home/index";
    }
	
	@GetMapping("/error")
	public String loginerror() {
		return "/error/error";
	}

	@GetMapping("/admin")
	public String adminhome() {
		return "/admin/index";
	}

	@GetMapping("/access-denied")
	public String accessdenied() {
		return "/error/access-denied";
	}
}
