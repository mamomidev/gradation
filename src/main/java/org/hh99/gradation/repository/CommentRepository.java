package org.hh99.gradation.repository;

import java.util.List;

import org.hh99.gradation.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByCardsId(Long cardId);
	Integer deleteAllByCardsId(Long cardId);
}
