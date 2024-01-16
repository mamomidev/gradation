package org.hh99.gradation.controller;

import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.ColumnsDto;
import org.hh99.gradation.service.ColumnsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ColumnsController {

    private final ColumnsService columnsService;

    @PostMapping("/board/{boardId}/columns")
    public void createColumns(@PathVariable Long boardId, @RequestBody ColumnsDto columnsDto) {
        columnsService.createColumns(boardId, columnsDto);
    }


}
