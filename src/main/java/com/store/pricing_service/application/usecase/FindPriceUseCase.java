package com.store.pricing_service.application.usecase;

import com.store.pricing_service.domain.model.Price;
import com.store.pricing_service.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FindPriceUseCase {

  private final PriceRepository priceRepository;

  // Constructor explícito para inyección de dependencias
  public FindPriceUseCase(PriceRepository priceRepository) {
    this.priceRepository = priceRepository;
  }

  public List<Price> getPrices(Integer productId, Integer brandId, LocalDateTime date) {
    return priceRepository.findPrices(productId, brandId, date);
  }
}