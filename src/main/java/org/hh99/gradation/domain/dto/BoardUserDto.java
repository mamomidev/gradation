package org.hh99.gradation.domain.dto;

import org.hh99.gradation.domain.entity.BoardUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BoardUserDto {

	private Long userId;
	private Long boardId;

	public BoardUserDto(BoardUser boardUser) {
		this.userId = boardUser.getUser().getId();
		this.boardId = boardUser.getBoard().getId();
	}
}
