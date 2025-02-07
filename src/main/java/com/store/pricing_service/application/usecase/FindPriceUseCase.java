package com.store.pricing_service.application.usecase;

import com.store.pricing_service.domain.model.Price;
import com.store.pricing_service.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPriceUseCase {

    private final PriceRepository priceRepository;

    public List<Price> getPrices(Integer productId, Integer brandId, LocalDateTime date) {
        return priceRepository.findPrices(productId, brandId, date);
    }
}