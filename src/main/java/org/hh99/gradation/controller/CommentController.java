package org.hh99.gradation.controller;

import java.util.List;

import org.hh99.gradation.domain.dto.CommentDto;
import org.hh99.gradation.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class CommentController {

	private final CommentService commentService;

	@GetMapping("/cards/comments/{commentId}")
	public List<CommentDto> getComments(@PathVariable Long commentId){
		return commentService.getComments(commentId);
	}

	@PostMapping("/cards/comments")
	public ResponseEntity createComments(@RequestBody CommentDto commentDto){
		return commentService.createComments(commentDto);
	}

	@DeleteMapping("/cards/comments/{commentId}")
	public ResponseEntity deleteComments(@PathVariable Long commentId){
		return commentService.deleteComments(commentId);
	}
}
