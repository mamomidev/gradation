package org.hh99.gradation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hh99.gradation.domain.dto.CommentDto;
import org.hh99.gradation.domain.entity.Card;
import org.hh99.gradation.domain.entity.Comment;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.CommentRepository;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	private final JwtUtil jwtUtil;

	public List<CommentDto> getComments(Long cardId) {
		return commentRepository.findAllByCardsId(cardId).stream()
			.map(CommentDto::new)
			.collect(Collectors.toList());
	}

	public ResponseEntity<CommentDto> createComments(CommentDto commentDto) {
		commentDto.setUser(userRepository.findByEmail(jwtUtil.getUserEmail()));
		return ResponseEntity.ok().body(new CommentDto(commentRepository.save(new Comment(commentDto))));
	}

	public ResponseEntity deleteComments(Long commentId) {
		userValidation(commentId);
		commentRepository.deleteById(commentId);
		return ResponseEntity.ok().build();
	}

	@Transactional
	public ResponseEntity updateComments(Long commentId, CommentDto commentDto) {
		Comment comment = userValidation(commentId);
		comment.setContents(commentDto.getContents());
		return ResponseEntity.ok().build();
	}

	private Comment userValidation(Long commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new EntityNotFoundException("없는 댓글 입니다."));
		if (!comment.getUsers().getEmail().equals(jwtUtil.getUserEmail())) {
			throw new IllegalArgumentException("댓글을 생성한 사람이 아닙니다.");
		}
		return comment;
	}
}
