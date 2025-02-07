package com.store.pricing_service.infrastructure.controller;

import com.store.pricing_service.infrastructure.dto.PriceResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceControllerTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    void shouldReturnCorrectPriceForTest1() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2020-06-14T10:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getPrice()).isEqualTo("35.50");
    }

    @Test
    void shouldReturnCorrectPriceForTest2() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2020-06-14T16:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getPrice()).isEqualTo("25.45");
    }

    @Test
    void shouldReturnCorrectPriceForTest3() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2020-06-14T21:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getPrice()).isEqualTo("35.50");
    }

    @Test
    void shouldReturnCorrectPriceForTest4() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2020-06-15T10:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getPrice()).isEqualTo("30.50");
    }

    @Test
    void shouldReturnCorrectPriceForTest5() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2020-06-16T21:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotEmpty();
        assertThat(response.getBody()[0].getPrice()).isEqualTo("38.95");
    }

    @Test
    void shouldReturnEmptyListWhenNoPriceAvailable() throws Exception {
        URI uri = new URI("http://localhost:" + port + "/api/prices?date=2023-01-01T10:00:00&productId=35455&brandId=1");
        ResponseEntity<PriceResponseDTO[]> response = restTemplate.getForEntity(uri, PriceResponseDTO[].class);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isEmpty();
    }
}
