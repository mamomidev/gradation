package org.hh99.gradation.service;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.domain.entity.User;
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

	//TODO 2024-01-16 14:18 생성
	// 컬럼 내부에 카드 생성
	public ResponseEntity createCard(CardDto cardDto) {
		cardDto.setUsers(userRepository.findByEmail(jwtUtil.getUserEmail()));
		// Board 체크?
		columnsRepository.findById(cardDto.getColumns().getId()).orElseThrow(() -> new EntityNotFoundException());

		cardRepository.save(new Card(cardDto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	//TODO 2024-01-16 14:18 수정
	// 이름
	// 설명
	// 색상
	// 작업자 할당/변경
	// 마감일
	@Transactional
	public ResponseEntity<CardDto> updateCard(Long cardId, CardDto cardDto) {
		Card card = userValidation(cardId);
		card.update(cardDto);
		return ResponseEntity.status(HttpStatus.OK).body(new CardDto(card));
	}

	//TODO 2024-01-16 14:19 삭제
	@Transactional
	public ResponseEntity deleteCard(Long cardId) {
		Card card = userValidation(cardId);
		cardRepository.delete(card);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	//TODO 2024-01-16 14:19 이동
	// 같은 컬럼 내에서 카드의 위치를 변경할 수 있어야 합니다.
	// 카드를 다른 컬럼으로 이동
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