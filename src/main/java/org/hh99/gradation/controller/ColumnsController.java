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

    @GetMapping("/boards/{boardId}/columns")
    public String getAllColumnsByBoardId(@PathVariable Long boardId, Model model) {
        List<ColumnsDto> columnsDtoList = columnsService.getAllColumnsByBoardId(boardId);
        model.addAttribute("columns", columnsDtoList);
        return "kanban";
    }

    @PostMapping("/boards/{boardId}/columns")
    public String createColumns(@PathVariable Long boardId, @RequestBody ColumnsDto columnsDto) {
        columnsService.createColumns(boardId, columnsDto);
        return "columns";
    }

    @PatchMapping("/boards/{boardId}/columns/{columnsId}")
    public String modifyColumns(@PathVariable Long boardId, @PathVariable Long columnsId, @RequestBody ColumnsDto columnsDto) {
        columnsService.modifyColumns(columnsId, columnsDto);
        return "redirect:/api/user/boards/{boardId}/columns";
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
