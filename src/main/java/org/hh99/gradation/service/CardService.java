package org.hh99.gradation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.CardRepository;
import org.hh99.gradation.repository.ColumnsRepository;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	private final UserRepository userRepository;
	private final CardRepository cardRepository;
	private final ColumnsRepository columnsRepository;
	private final JwtUtil jwtUtil;

	public List<CardDto> getCards(Long columnsId) {
		return cardRepository.findAllByColumnsId(columnsId).stream()
			.map(CardDto::new)
			.collect(Collectors.toList());
	}

	public ResponseEntity createCard(CardDto cardDto) {
		cardDto.setUsers(userRepository.findByEmail(jwtUtil.getUserEmail()));
		// Board 체크?
		columnsRepository.findById(cardDto.getColumns().getId()).orElseThrow(() -> new EntityNotFoundException());

		cardRepository.save(new Card(cardDto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Transactional
	public ResponseEntity<CardDto> updateCard(Long cardId, CardDto cardDto) {
		Card card = userValidation(cardId);
		card.update(cardDto);
		return ResponseEntity.status(HttpStatus.OK).body(new CardDto(card));
	}

	@Transactional
	public ResponseEntity deleteCard(Long cardId) {
		Card card = userValidation(cardId);
		cardRepository.delete(card);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@Transactional
	public ResponseEntity moveCard(Long cardId, CardDto cardDto) {
		Card card = userValidation(cardId);
		card.move(cardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private Card userValidation(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("없는 카드 입니다."));
		if (!card.getUsers().getEmail().equals(jwtUtil.getUserEmail())) {
			throw new IllegalArgumentException("카드를 생성한 사람이 아닙니다.");
		}
		return card;
	}
}