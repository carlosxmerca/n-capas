package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	@GetMapping("/greeting")
	public String getGreet() {
		return "greeting";
	}
	
	@GetMapping("/say-hello")
	public String getHello(@RequestParam(name = "name", defaultValue = "no one") String name, Model model) {
		model.addAttribute("someone", name);
		
		return "say-hello";
	}

	
}
