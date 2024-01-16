package org.hh99.gradation.service;

import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.ColumnsDto;
import org.hh99.gradation.domain.entity.Board;
import org.hh99.gradation.domain.entity.Columns;
import org.hh99.gradation.repository.BoardRepository;
import org.hh99.gradation.repository.ColumnsRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnsService {

    private final ColumnsRepository columnsRepository;
    private final BoardRepository boardRepository;
    public void createColumns(Long boardId, ColumnsDto columnsDto) {
        Board board = boardRepository.findById(boardId).orElseThrow();
        Columns columns = new Columns(columnsDto, board);
        columnsRepository.save(columns);
    }
}
