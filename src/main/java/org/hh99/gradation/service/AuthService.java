package org.hh99.gradation.service;

import org.hh99.gradation.config.PasswordConfig;
import org.hh99.gradation.domain.dto.UserDto;
import org.hh99.gradation.domain.entity.User;
import org.hh99.gradation.message.ErrorMessage;
import org.hh99.gradation.message.SuccessMessage;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public ResponseEntity signup(UserDto userDto) {
		if(userRepository.findByEmail(userDto.getEmail()) != null){
			throw new IllegalArgumentException(ErrorMessage.EMAIL_FORMAT_ERROR_MESSAGE.getErrorMessage());
		}

		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userRepository.save(new User(userDto));
		return ResponseEntity.ok(SuccessMessage.JOIN_SUCCESS_MESSAGE.getSuccessMessage());
	}
}