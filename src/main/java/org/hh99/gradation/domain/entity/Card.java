package org.hh99.gradation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long id;

    @Column(name = "card_name")
    private String cardName;

    @Column(name = "card_color")
    private String cardColor;

    @Column(name = "card_order")
    private Integer cardOrder;

    @Column(name = "deadline")
    private LocalDate deadLine;

    @Column(name = "url")
    private String url;
}

