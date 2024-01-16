package org.hh99.gradation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	@GetMapping("/hello")
	public String index(){
		return "/mainpage.html";
	}
}
