package org.hh99.gradation.repository;

import java.util.List;

import org.hh99.gradation.domain.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
	List<Card> findAllByColumnsIdOrderByCardOrder(Long ColumnsId);
}
