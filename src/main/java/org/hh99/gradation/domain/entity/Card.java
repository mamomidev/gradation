package org.hh99.gradation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.hh99.gradation.domain.dto.CardDto;

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
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "columns_id")
    private Columns columns;

    @Column
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
}