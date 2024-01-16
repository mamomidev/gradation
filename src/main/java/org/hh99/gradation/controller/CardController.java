package org.hh99.gradation.controller;

import java.util.List;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.dto.CommentDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class CardController {

	private final CardService cardService;

	@GetMapping("/cards/{columnsId}")
	public List<CardDto> getCards(@PathVariable Long columnsId) {
		return cardService.getCards(columnsId);
	}

	@PostMapping("/cards")
	public ResponseEntity createCard(@RequestBody CardDto cardDto) {
		return cardService.createCard(cardDto);
	}

	@PatchMapping("/cards/{cardId}")
	public ResponseEntity updateCard(@PathVariable Long cardId, @RequestBody CardDto cardDto) {
		return cardService.updateCard(cardId, cardDto);
	}

	@DeleteMapping("/cards/{cardId}")
	public ResponseEntity deleteCard(@PathVariable Long cardId) {
		return cardService.deleteCard(cardId);
	}

	@PatchMapping("/cards/{cardId}/move")
	public ResponseEntity moveCard(@PathVariable Long cardId, @RequestBody CardDto cardDto) {
		return cardService.moveCard(cardId, cardDto);
	}
}