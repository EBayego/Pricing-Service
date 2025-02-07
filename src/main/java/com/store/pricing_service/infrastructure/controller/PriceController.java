package com.store.pricing_service.infrastructure.controller;

import com.store.pricing_service.application.usecase.FindPriceUseCase;
import com.store.pricing_service.domain.model.Price;
import com.store.pricing_service.infrastructure.dto.PriceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

    private final FindPriceUseCase findPriceUseCase;

    @GetMapping
    public ResponseEntity<PriceResponseDTO> getPrice(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date,
            @RequestParam("productId") Integer productId,
            @RequestParam("brandId") Integer brandId) {

        Optional<Price> priceOptional = findPriceUseCase.getApplicablePrice(productId, brandId, date);

        return priceOptional.map(price -> ResponseEntity.ok(
                PriceResponseDTO.builder()
                        .productId(price.getProductId())
                        .brandId(price.getBrandId())
                        .priceList(price.getPriceList())
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate())
                        .price(price.getPrice())
                        .build()
        )).orElse(ResponseEntity.notFound().build());
    }
}