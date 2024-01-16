package org.hh99.gradation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ColumnsDto {

	private Long id;
	private Long boardId;
	private String columnsName;
	private Integer columnsOrder;
}
