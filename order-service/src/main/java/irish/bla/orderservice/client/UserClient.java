package irish.bla.orderservice.client;

import irish.bla.orderservice.dto.TransactionRequestDto;
import irish.bla.orderservice.dto.TransactionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserClient {
    private final WebClient webClient;

    public UserClient(@Value("${user.service.url}") String url) {
        this.webClient = WebClient.builder()
                .baseUrl(url)
                .build();
    }

    public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto request) {
        return this.webClient
                .post()
                .uri("transaction")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class);
    }

}
