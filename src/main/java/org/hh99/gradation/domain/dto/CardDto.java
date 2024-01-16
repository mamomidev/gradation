package org.hh99.gradation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.domain.entity.Comment;
import org.hh99.gradation.domain.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private Long id;
    private User users;
    private Columns columns;
    private String cardName;
    private String cardColor;
    private Integer cardOrder;
    private LocalDate deadLine;
    private String url;
}
