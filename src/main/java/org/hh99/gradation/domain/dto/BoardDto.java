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
	private String boardName;
	private String boardColor;
	private String boardDescription;

	public BoardDto(Board entity) {
		this.id = entity.getId();
		this.boardName = entity.getBoardName();
		this.boardColor = entity.getBoardColor();
		this.boardDescription = entity.getBoardDescription();
	}
}
