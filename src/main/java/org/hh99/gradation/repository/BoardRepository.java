package org.hh99.gradation.repository;

import java.util.List;

import org.hh99.gradation.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
	List<Board> findAllByCreateUserId(Long userId);
}
