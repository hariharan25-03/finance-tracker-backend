package org.example.repository;

import org.example.entity.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, UUID> {
    @Query(value = "SELECT * FROM PORTFOLIOS WHERE USER_ID = :userId", nativeQuery = true)
    List<Portfolio> findByUserId(UUID userId);

}
