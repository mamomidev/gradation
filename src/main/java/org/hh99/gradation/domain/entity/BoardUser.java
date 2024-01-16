package org.hh99.gradation.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "boardusers")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private User user;

	@ManyToOne
	@JoinColumn
	private Board board;

	public BoardUser(Board board, User user) {
		this.board = board;
		this.user = user;
	}
}
