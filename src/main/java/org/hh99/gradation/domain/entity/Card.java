package org.hh99.gradation.domain.entity;

import java.time.LocalDate;

import org.hh99.gradation.domain.dto.CardDto;

import jakarta.persistence.Column;
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

@Entity
@Getter
@Table(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
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
        this.cardName = cardDto.getCardName();
        this.cardDescription = cardDto.getCardDescription();
        this.cardColor = cardDto.getCardColor();
        this.cardOrder = cardDto.getCardOrder();
        this.deadLine = cardDto.getDeadLine();
        this.worker = cardDto.getWorker();
        this.columns = cardDto.getColumns();
    }

    public void update(CardDto cardDto){
        this.cardName = cardDto.getCardName();
        this.cardDescription = cardDto.getCardDescription();
        this.cardColor = cardDto.getCardColor();
        this.worker = cardDto.getWorker();
    }

    public void move(CardDto cardDto){
        this.cardOrder = cardDto.getCardOrder();
    }
}