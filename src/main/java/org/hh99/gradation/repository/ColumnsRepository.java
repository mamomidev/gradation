package org.hh99.gradation.repository;

import org.hh99.gradation.domain.entity.Columns;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ColumnsRepository extends JpaRepository<Columns, Long> {
    List<Columns> findByBoardIdOrderByColumnsOrder(Long boardId);
}
