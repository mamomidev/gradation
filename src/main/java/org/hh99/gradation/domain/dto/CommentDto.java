package org.hh99.gradation.domain.dto;

import org.hh99.gradation.domain.entity.Card;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

	private Long id;
	private Long cardId;
	private Long userId;
	private String contents;
	private Long parent_id;
}
