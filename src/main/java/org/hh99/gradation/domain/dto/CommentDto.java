package org.hh99.gradation.domain.dto;

import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.domain.entity.User;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
	private Long id;
	private Card card;
	@Setter
	private User user;
	private String contents;
	private Integer parentId;
}
