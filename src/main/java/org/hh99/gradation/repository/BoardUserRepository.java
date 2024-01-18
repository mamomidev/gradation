package org.hh99.gradation.repository;

import java.util.List;

import org.hh99.gradation.domain.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {
	List<BoardUser> findAllByUserId(Long userId);

	List<BoardUser> findAllByBoardId(Long boardId);
}
