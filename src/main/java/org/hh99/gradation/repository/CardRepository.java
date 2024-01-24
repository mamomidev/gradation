package org.hh99.gradation.repository;

import java.util.List;
import java.util.Optional;

import io.lettuce.core.dynamic.annotation.Param;
import org.hh99.gradation.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardRepository extends JpaRepository<Card, Long> {
	List<Card> findAllByColumnsIdOrderByCardOrder(Long ColumnsId);

	@Query("SELECT c FROM Card c JOIN FETCH c.columns col JOIN FETCH c.users u WHERE c.id = :cardId")
	Optional<Card> findByCardId (@Param("cardId") Long cardId);
}
