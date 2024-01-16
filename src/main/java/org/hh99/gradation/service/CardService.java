package org.hh99.gradation.service;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.CardRepository;
import org.hibernate.annotations.processing.Find;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;
	private final JwtUtil jwtUtil;

	//TODO 2024-01-16 14:18 생성
	// 컬럼 내부에 카드 생성
	public ResponseEntity createCard(CardDto cardDto) {
		if (cardDto.getCardOrder() == null) {
			cardDto.setCardOrder(1);
		}

		cardRepository.save(new Card(cardDto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	//TODO 2024-01-16 14:18 수정
	// 이름
	// 설명
	// 색상
	// 작업자 할당/변경
	// 마감일
	public ResponseEntity updateCard(Long cardId, CardDto cardDto) {
		Card card = userValidation(cardId);
		card.cardInfoUpdate(cardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	//TODO 2024-01-16 14:19 삭제
	public ResponseEntity deleteCard(Long cardId) {
		Card card = userValidation(cardId);
		cardRepository.delete(card);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	//TODO 2024-01-16 14:19 이동
	// 같은 컬럼 내에서 카드의 위치를 변경할 수 있어야 합니다.
	// 카드를 다른 컬럼으로 이동
	public ResponseEntity moveCard(Long cardId, CardDto cardDto) {
		Card card = userValidation(cardId);
		card.cardMove(cardDto);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	private Card userValidation(Long cardId) {
		Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException());
		if (card.getUsers().getId() != jwtUtil.getUserId()) {
			// 유저 일치 안함
		}
		return card;
	}
}