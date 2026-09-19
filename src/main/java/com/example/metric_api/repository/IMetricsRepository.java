package com.example.metric_api.repository;

import com.example.metric_api.entitiy.Metrics;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IMetricsRepository extends JpaRepository<Metrics, Long> {
    Page<Metrics> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Metrics m WHERE m.createdAt < :thershold")
    int deleteOlderThan(@Param("thershold") LocalDateTime thershold);
}
