package org.hh99.gradation.controller;

import java.io.IOException;
import java.util.List;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.service.CardService;
import org.hh99.gradation.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CardController {

	private final CardService cardService;
	private final CommentService commentService;
	private final JwtUtil jwtUtil;
	@GetMapping("/cards/{cardId}")
	public String cards(@PathVariable Long cardId, Model model){
		model.addAttribute("card", cardService.getCard(cardId));
		model.addAttribute("comment", commentService.getComments(cardId));
		model.addAttribute("userEmail", jwtUtil.getUserEmail());
		return "card";
	}

	@PostMapping("/api/cards")
	public ResponseEntity<String> createCard(@RequestBody CardDto cardDto, @RequestPart(value = "file", required = false) MultipartFile file) throws
		IOException {
		return cardService.createCard(cardDto, file);
	}

	@PatchMapping("/api/cards/{cardId}")
	public ResponseEntity<CardDto> updateCard(@PathVariable Long cardId, @RequestBody CardDto cardDto) {
		return cardService.updateCard(cardId, cardDto);
	}

	@DeleteMapping("/api/cards/{cardId}")
	public ResponseEntity<String> deleteCard(@PathVariable Long cardId) {
		return cardService.deleteCard(cardId);
	}

	@PatchMapping("/api/cards/{cardId}/move")
	public ResponseEntity<String> moveCard(@PathVariable Long cardId, @RequestBody CardDto cardDto) {
		return cardService.moveCard(cardId, cardDto);
	}
}