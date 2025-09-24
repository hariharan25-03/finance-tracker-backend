package org.example.repository;

import org.example.entity.Asset;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AssetRepository  extends JpaRepository<Asset, UUID>  {

    @Query(value = "SELECT * FROM ASSETS WHERE TICKER = :ticker", nativeQuery = true)
    Optional<Asset> findByTicker(String ticker);

}
