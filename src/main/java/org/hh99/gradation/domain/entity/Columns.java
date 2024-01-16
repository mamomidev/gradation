package org.hh99.gradation.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hh99.gradation.domain.dto.ColumnsDto;

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
    private String columnsName;

    @Column
    private Integer columnsOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Board board;

    public Columns(ColumnsDto columnsDto,Board board) {
        this.columnsName = columnsDto.getColumnsName();
        this.columnsOrder = columnsDto.getColumnsOrder();
        this.board = board;
    }

    public void modify(ColumnsDto columnsDto) {
        this.columnsName = columnsDto.getColumnsName();
        this.columnsOrder = columnsDto.getColumnsOrder();
    }
}
