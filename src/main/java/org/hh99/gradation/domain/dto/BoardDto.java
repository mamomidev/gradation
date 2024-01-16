package org.hh99.gradation.domain.dto;

import org.hh99.gradation.domain.entity.Board;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

	private Long id;
	@NotNull(message = "보드 이름을 입력해주세요.")
	private String boardName;
	@NotNull(message = "보드 색상 정해주세요.")
	private String boardColor;
	@NotNull(message = "보드 설명을 입력해주세요.")
	private String boardDescription;

	public BoardDto(Board entity) {
		this.boardName = entity.getBoardName();
		this.boardColor = entity.getBoardColor();
		this.boardDescription = entity.getBoardDescription();
	}
}
