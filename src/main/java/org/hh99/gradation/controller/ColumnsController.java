package org.hh99.gradation.controller;

import lombok.RequiredArgsConstructor;
import org.hh99.gradation.domain.dto.ColumnsDto;
import org.hh99.gradation.service.ColumnsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class ColumnsController {

    private final ColumnsService columnsService;

    @GetMapping("/board/{boardId}/columns")
    public String getAllColumnsByBoardId(@PathVariable Long boardId, Model model) {

        List<ColumnsDto> columnsDtoList = columnsService.getAllColumnsByBoardId(boardId);
        model.addAttribute("columns", columnsDtoList);

        return "columns";
    }

    @PostMapping("/board/{boardId}/columns")
    public String createColumns(@PathVariable Long boardId, @RequestBody ColumnsDto columnsDto) {
        columnsService.createColumns(boardId, columnsDto);
        return "columns";
    }

    @PatchMapping("/columns/{columnsId}")
    public void modifyColumns(@PathVariable Long columnsId, @RequestBody ColumnsDto columnsDto) {
        columnsService.modifyColumns(columnsId, columnsDto);
    }

    @PatchMapping("/columns/{columnsId}/order")
    public void modifyColumnsOrder(@PathVariable Long columnsId, @RequestBody ColumnsDto columnsDto) {
        columnsService.modifyColumnsOrder(columnsId, columnsDto);
    }

    @DeleteMapping("/columns/{columnsId}")
    public void deleteColumns(@PathVariable Long columnsId) {
        columnsService.deleteColumns(columnsId);
    }
}
