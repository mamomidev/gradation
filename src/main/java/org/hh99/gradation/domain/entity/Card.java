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
    @Column
    private Long id;

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