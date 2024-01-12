package org.hh99.gradation.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;
    private String cardName;
    private String cardColor;
    private Integer cardOrder;
    private LocalDate deadLine;
    private String url;

}
