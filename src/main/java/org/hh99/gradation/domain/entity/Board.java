package org.hh99.gradation.domain.entity;

import org.hh99.gradation.domain.dto.BoardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "boards")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String boardName;

	@Column(nullable = false)
	private String boardColor;

	@Column(nullable = false)
	private String boardDescription;

	@Column
	private Long createUserId;

	public Board(BoardDto boardDto, Long userId) {
		this.boardName = boardDto.getBoardName();
		this.boardColor = boardDto.getBoardColor();
		this.boardDescription = boardDto.getBoardDescription();
		this.createUserId = userId;
	}

	public void update(BoardDto boardDto) {
		this.boardName = boardDto.getBoardName();
		this.boardColor = boardDto.getBoardColor();
		this.boardDescription = boardDto.getBoardDescription();
	}

}
