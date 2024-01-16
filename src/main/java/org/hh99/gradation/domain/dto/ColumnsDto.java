package org.hh99.gradation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hh99.gradation.domain.entity.Board;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnsDto {

	private Long id;
	private Board board;
	private String columnsName;
	private Integer columnsOrder;
}
