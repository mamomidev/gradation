package org.hh99.gradation.service;

import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.ColumnsDto;
import org.hh99.gradation.domain.entity.Board;
import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.repository.BoardRepository;
import org.hh99.gradation.repository.ColumnsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnsService {

    private final ColumnsRepository columnsRepository;
    private final BoardRepository boardRepository;
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
        Columns columns = columnsRepository.findById(columnsId).orElseThrow();
        columnsRepository.delete(columns);
    }

    @Transactional
    public void modifyColumnsOrder(Long columnsId, ColumnsDto columnsDto) {
        Columns columns = columnsRepository.findById(columnsId).orElseThrow();
        columns.modifyColumnsOrder(columnsDto);
    }

    public List<ColumnsDto> getAllColumnsByBoardId(Long boardId) {
        return columnsRepository.findByBoardId(boardId).stream().map(ColumnsDto::new).toList();

    }
}
