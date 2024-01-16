package org.hh99.gradation.service;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.repository.CardRepository;
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

	//TODO 2024-01-16 14:18 생성
	// 컬럼 내부에 카드 생성
	public ResponseEntity createCard(CardDto cardDto) {

		if (cardDto.getUsers() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (cardDto.getColumns() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		if (StringUtils.isBlank(cardDto.getCardName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

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
		// 자기 자신인지 확인 필요
		Card card = cardRepository.findById(cardId).orElseThrow(()-> new EntityNotFoundException());
		card.cardInfoUpdate(cardDto);
		return null;
	}

	//TODO 2024-01-16 14:19 삭제
	public ResponseEntity deleteCard(Long cardId) {
		// 자기 자신인지 확인 필요
		cardRepository.deleteById(cardId);
		return null;
	}

	//TODO 2024-01-16 14:19 이동
	// 같은 컬럼 내에서 카드의 위치를 변경할 수 있어야 합니다.
	// 카드를 다른 컬럼으로 이동
	public ResponseEntity moveCard(Long cardId, CardDto cardDto) {
		// 자기 자신인지 확인 필요
		Card card = cardRepository.findById(cardId).orElseThrow(()-> new EntityNotFoundException());
		card.cardMove(cardDto);
		return null;
	}
}