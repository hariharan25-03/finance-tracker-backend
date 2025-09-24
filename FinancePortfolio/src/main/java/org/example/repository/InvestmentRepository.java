package org.example.repository;

import org.example.entity.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, UUID>  {
    @Query(value = "SELECT * FROM Investments WHERE PORTFOLIO_ID = :portfolioId", nativeQuery = true)
    List<Investment> findByPortfolioId(UUID portfolioId);
}
