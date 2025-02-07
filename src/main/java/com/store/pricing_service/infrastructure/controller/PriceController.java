package com.store.pricing_service.infrastructure.controller;

import com.store.pricing_service.application.usecase.FindPriceUseCase;
import com.store.pricing_service.domain.model.Price;
import com.store.pricing_service.infrastructure.dto.PriceResponseDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
public class PriceController {

  private final FindPriceUseCase findPriceUseCase;

  @GetMapping
  public ResponseEntity<List<PriceResponseDTO>> getPrices(
      @RequestParam(value = "date", required = false)
          @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
          LocalDateTime date,
      @RequestParam(value = "productId", required = false) Integer productId,
      @RequestParam(value = "brandId", required = false) Integer brandId) {

    List<Price> prices = findPriceUseCase.getPrices(productId, brandId, date);

    List<PriceResponseDTO> response =
        prices.stream()
            .map(
                price ->
                    PriceResponseDTO.builder()
                        .productId(price.getProductId())
                        .brandId(price.getBrandId())
                        .priceList(price.getPriceList())
                        .startDate(price.getStartDate())
                        .endDate(price.getEndDate())
                        .price(price.getPrice())
                        .build())
            .collect(Collectors.toList());

    return ResponseEntity.ok(response);
  }
}
