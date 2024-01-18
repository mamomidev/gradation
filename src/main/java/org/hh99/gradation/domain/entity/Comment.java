package org.hh99.gradation.domain.entity;

import org.hh99.gradation.domain.dto.CommentDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "card_id", nullable = false)
	private Card cards;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User users;

	@Column
	private String contents;

	@Column
	private Integer parentId;

	public Comment(CommentDto commentDto) {
		this.cards = commentDto.getCard();
		this.users = commentDto.getUser();
		this.contents = commentDto.getContents();
		this.parentId = commentDto.getParentId();
	}
}
