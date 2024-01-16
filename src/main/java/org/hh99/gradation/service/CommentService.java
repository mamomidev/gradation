package org.hh99.gradation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.hh99.gradation.domain.dto.CommentDto;
import org.hh99.gradation.domain.entity.Comment;
import org.hh99.gradation.jwt.JwtUtil;
import org.hh99.gradation.repository.CommentRepository;
import org.hh99.gradation.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository commentRepository;
	private final UserRepository userRepository;

	private final JwtUtil jwtUtil;

	public List<CommentDto> getComments(Long cardId){
		return commentRepository.findAllByCardsId(cardId).stream()
			.map(CommentDto::new)
			.collect(Collectors.toList());
	}

	public ResponseEntity createComments(CommentDto commentDto){
		commentDto.setUser(userRepository.findByEmail(jwtUtil.getUserEmail()));
		commentRepository.save(new Comment(commentDto));
		return ResponseEntity.ok().build();
	}

	public ResponseEntity deleteComments(Long commentId){
		commentRepository.deleteById(commentId);
		return ResponseEntity.ok().build();
	}
}
