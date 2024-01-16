package org.hh99.gradation.service;

import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.repository.CardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

	private final CardRepository cardRepository;

	//TODO 2024-01-16 14:18 생성
	// 컬럼 내부에 카드 생성
	public ResponseEntity createCard(CardDto cardDto) {
		return null;
	}

	//TODO 2024-01-16 14:18 수정
	// 이름
	// 설명
	// 색상
	// 작업자 할당/변경
	// 마감일
	public ResponseEntity updateCard() {
		return null;
	}

	//TODO 2024-01-16 14:19 삭제
	public ResponseEntity deleteCard() {
		return null;
	}

	//TODO 2024-01-16 14:19 이동
	// 같은 컬럼 내에서 카드의 위치를 변경할 수 있어야 합니다.
	// 카드를 다른 컬럼으로 이동
	public ResponseEntity moveCard() {
		return null;
	}
}
