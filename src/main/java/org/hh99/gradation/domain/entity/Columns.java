package org.hh99.gradation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String columnName;

    @Column
    private Integer columnOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Board board;
}
