package org.hh99.gradation.service;

import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.CardDto;
import org.hh99.gradation.domain.dto.ColumnsDto;
import org.hh99.gradation.domain.entity.Board;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.repository.BoardRepository;
import org.hh99.gradation.repository.CardRepository;
import org.hh99.gradation.repository.ColumnsRepository;
import org.hh99.gradation.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnsService {

    private final BoardRepository boardRepository;
    private final ColumnsRepository columnsRepository;
    private final CardRepository cardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void createColumns(Long boardId, ColumnsDto columnsDto) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Columns columns = new Columns(columnsDto, board);
        columnsRepository.save(columns);
    }

    @Transactional
    public void modifyColumns(Long columnsId, ColumnsDto columnsDto) {
        Columns columns = columnsRepository.findById(columnsId).orElseThrow();
        columns.modifyColumnsName(columnsDto);
    }

    @Transactional
    public void deleteColumns(Long columnsId) {
        List<Card> cardList = cardRepository.findAllByColumnsIdOrderByCardOrder(columnsId).stream().toList();

        cardList.forEach(card -> {
            commentRepository.deleteAllByCardsId(card.getId());
            cardRepository.delete(card);
        });

        Columns columns = columnsRepository.findById(columnsId).orElseThrow();
        columnsRepository.delete(columns);
    }

    @Transactional
    public void modifyColumnsOrder(Long columnsId, ColumnsDto columnsDto) {
        Columns columns = columnsRepository.findById(columnsId).orElseThrow();
        columns.modifyColumnsOrder(columnsDto);
    }

    public List<ColumnsDto> getAllColumnsByBoardId(Long boardId) {
        List<ColumnsDto> columnsDtoList = columnsRepository.findByBoardIdOrderByColumnsOrder(boardId).stream().map(ColumnsDto::new).toList();
        columnsDtoList.forEach(e -> {
            List<CardDto> cardDtoList = cardRepository.findAllByColumnsIdOrderByCardOrder(e.getId()).stream().map(CardDto::new).toList();
            e.setCardList(cardDtoList);
        });
        return columnsDtoList;

    }
}
