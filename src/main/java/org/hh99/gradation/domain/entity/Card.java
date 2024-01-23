package org.hh99.gradation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hh99.gradation.domain.dto.CardDto;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@ManyToOne
	@JoinColumn(name = "users_id", nullable = false)
	private User users;

	@ManyToOne
	@JoinColumn(name = "columns_id", nullable = false)
	private Columns columns;

	@Column(nullable = false)
	private String cardName;

	@Column
	private String cardDescription;

	@Column
	private String cardColor;

	@Column
	private Integer cardOrder;

	@Column
	private LocalDate deadLine;

	@Column
	private String url;

	@Column
	private String worker;

	public Card(CardDto cardDto) {
		this.users = cardDto.getUsers();
		this.columns = cardDto.getColumns();
		this.cardName = cardDto.getCardName();
		this.cardDescription = cardDto.getCardDescription();
		this.cardColor = cardDto.getCardColor();
		this.cardOrder = cardDto.getCardOrder();
		this.deadLine = cardDto.getDeadLine();
		this.worker = cardDto.getWorker();
	}

	public void update(CardDto cardDto) {
		this.cardName = cardDto.getCardName();
		this.cardDescription = cardDto.getCardDescription();
		this.cardColor = cardDto.getCardColor();
		this.worker = cardDto.getWorker();
	}

	public void move(CardDto cardDto) {
		if (cardDto.getCardOrder() != null) {
			this.cardOrder = cardDto.getCardOrder();
		}
		if (cardDto.getColumns() != null) {
			this.columns = cardDto.getColumns();
		}
	}
}