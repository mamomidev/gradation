package org.hh99.gradation.domain.dto;

import java.time.LocalDate;

import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.domain.entity.User;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    @Setter
    private User users;

    @NotNull(message = "컬럼을 선택해주세요.")
    private Columns columns;

    @NotEmpty(message = "카드명을 입력해주세요.")
    private String cardName;

    private String cardDescription;

    private String cardColor;

    private int cardOrder;

    private LocalDate deadLine;

    private String url;

    private String worker;
}
