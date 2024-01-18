package org.hh99.gradation.controller;

import org.hh99.gradation.domain.UserAuthEnum;
import org.hh99.gradation.domain.dto.UserDto;
import org.hh99.gradation.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody UserDto userDto){
		userDto.setAuthor(UserAuthEnum.USER);
		return authService.signup(userDto);
	}
	@GetMapping("/toSignup")
	public String toSignup() {
		return "signup";
	}
}