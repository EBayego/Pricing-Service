package com.store.pricing_service.domain.repository;

import com.store.pricing_service.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

  @Query("SELECT p FROM Price p " +
          "WHERE (:productId IS NULL OR p.productId = :productId) " +
          "AND (:brandId IS NULL OR p.brandId = :brandId) " +
          "AND (:date IS NULL OR (:date BETWEEN p.startDate AND p.endDate)) " +
          "ORDER BY p.priority DESC")
  List<Price> findPrices(
          @Param("productId") Integer productId,
          @Param("brandId") Integer brandId,
          @Param("date") LocalDateTime date);
}
