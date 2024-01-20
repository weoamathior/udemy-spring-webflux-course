package irish.bla.orderservice.client;

import irish.bla.orderservice.dto.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class ProductClient {
    private final WebClient webClient;
    public ProductClient(@Value("${product.service.url}") String serviceUrl) {
        webClient = WebClient.builder()
                .baseUrl(serviceUrl)
                .build();
    }

    public Mono<ProductDto> getProductById(final String productId) {
        return this.webClient.get()
                .uri("{id}", productId)
                .retrieve()
                .bodyToMono(ProductDto.class);
    }

    public Flux<ProductDto> getAllProducts() {
        return webClient.get()
                .uri("all")
                .retrieve()
                .bodyToFlux(ProductDto.class);
    }
}
