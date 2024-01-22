package org.hh99.gradation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hh99.gradation.domain.entity.Board;
import org.hh99.gradation.domain.entity.Columns;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnsDto {

	private Long id;
	private Board board;
	private String columnsName;
	private Integer columnsOrder;
	private List<CardDto> cardList;

	public ColumnsDto(Columns entity) {
		this.id = entity.getId();
		this.board = entity.getBoard();
		this.columnsName = entity.getColumnsName();
		this.columnsOrder = entity.getColumnsOrder();
	}
}
