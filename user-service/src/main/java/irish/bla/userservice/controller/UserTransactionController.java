package irish.bla.userservice.controller;

import irish.bla.userservice.dto.TransactionRequestDto;
import irish.bla.userservice.dto.TransactionResponseDto;
import irish.bla.userservice.entity.UserTransaction;
import irish.bla.userservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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

    /*
    curl http://localhost:8092/user/transaction\?userId\=3 | jq
     */
    @GetMapping
    public Flux<UserTransaction> getByUserId(@RequestParam("userId") Integer userId) {
        return transactionService.getByUserId(userId);

    }
}
