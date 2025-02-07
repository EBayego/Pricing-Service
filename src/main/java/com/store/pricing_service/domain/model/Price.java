package com.store.pricing_service.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "brand_id", nullable = false)
  private Integer brandId;

  @Column(name = "start_date", nullable = false)
  private LocalDateTime startDate;

  @Column(name = "end_date", nullable = false)
  private LocalDateTime endDate;

  @Column(name = "price_list", nullable = false)
  private Integer priceList;

  @Column(name = "product_id", nullable = false)
  private Integer productId;

  @Column(name = "priority", nullable = false)
  private Integer priority;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "curr", nullable = false)
  private String currency;
}
