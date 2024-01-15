package irish.bla.userservice.controller;

import irish.bla.userservice.dto.TransactionRequestDto;
import irish.bla.userservice.dto.TransactionResponseDto;
import irish.bla.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public Mono<TransactionResponseDto> createTransaction(@RequestBody Mono<TransactionRequestDto> request) {
        return request.flatMap(transactionService::createTransaction);
    }
}
