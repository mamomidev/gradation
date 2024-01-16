package org.hh99.gradation.domain.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.domain.entity.Comment;
import org.hh99.gradation.domain.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private Long id;

    @NotNull(message = "없는 회원 입니다.")
    private User users;

    @NotNull(message = "없는 컬럼 입니다.")
    private Columns columns;

    @NotEmpty(message = "카드명을 입력해주세요.")
    private String cardName;

    private String cardDescription;

    private String cardColor;

    @Setter
    private Integer cardOrder;

    private LocalDate deadLine;

    private String url;
}
