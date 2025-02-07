package com.store.pricing_service.application.usecase;

import com.store.pricing_service.domain.model.Price;
import com.store.pricing_service.domain.repository.PriceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FindPriceUseCaseTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private FindPriceUseCase findPriceUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPricesWhenDataExists() {
        LocalDateTime requestDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        Price mockPrice = Price.builder()
                .productId(productId)
                .brandId(brandId)
                .priceList(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                .price(new BigDecimal("35.50"))
                .build();

        when(priceRepository.findPrices(productId, brandId, requestDate)).thenReturn(List.of(mockPrice));

        List<Price> result = findPriceUseCase.getPrices(productId, brandId, requestDate);

        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getPrice()).isEqualTo(new BigDecimal("35.50"));

        verify(priceRepository, times(1)).findPrices(productId, brandId, requestDate);
    }

    @Test
    void shouldReturnEmptyListWhenNoData() {
        LocalDateTime requestDate = LocalDateTime.of(2023, 1, 1, 10, 0);
        Integer productId = 35455;
        Integer brandId = 1;

        when(priceRepository.findPrices(productId, brandId, requestDate)).thenReturn(List.of());

        List<Price> result = findPriceUseCase.getPrices(productId, brandId, requestDate);

        assertThat(result).isEmpty();
    }
}
