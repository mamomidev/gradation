package org.hh99.gradation.controller;

import java.util.List;

import org.hh99.gradation.domain.dto.BoardDto;
import org.hh99.gradation.domain.dto.UserDto;
import org.hh99.gradation.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

	private final BoardService boardService;

	@PostMapping("/boards")
	public ResponseEntity<Object> createBoard(@RequestBody BoardDto boardDto) {
		boardService.createBoard(boardDto);
		return ResponseEntity.ok("Board 생성 성공");
	}

	@GetMapping("/boards")
	public String getBoardList(Model model){
		model.addAttribute("boardList", boardService.getBoardList());
		return "boardlist";
	}

	@GetMapping("/boards/{boardId}")
	public ResponseEntity<Object> getBoard(@PathVariable Long boardId) {
		return ResponseEntity.ok(boardService.getBoard(boardId));
	}

	@PatchMapping("/boards/{boardId}")
	public ResponseEntity<Object> updateBoard(@RequestBody BoardDto boardDto, @PathVariable Long boardId) {
		boardService.updateBoard(boardDto, boardId);
		return ResponseEntity.ok("board 수정 성공");
	}

	@DeleteMapping("/boards/{boardId}")
	public ResponseEntity<Object> deleteBoard(@PathVariable Long boardId) {
		boardService.deleteBoard(boardId);
		return ResponseEntity.ok("board 삭제 성공");
	}

	@PostMapping("/boards/{boardId}/invite")
	public ResponseEntity<Object> inviteBoard(@PathVariable Long boardId, @RequestBody List<UserDto> userlist){
		boardService.inviteBoard(boardId, userlist);
		return ResponseEntity.ok("초대되었습니다.");
	}
}
