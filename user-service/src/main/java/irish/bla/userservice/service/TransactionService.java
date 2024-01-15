package irish.bla.userservice.service;

import irish.bla.userservice.dto.TransactionRequestDto;
import irish.bla.userservice.dto.TransactionResponseDto;
import irish.bla.userservice.dto.TransactionStatus;
import irish.bla.userservice.repository.UserRepository;
import irish.bla.userservice.repository.UserTransactionRepository;
import irish.bla.userservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final UserTransactionRepository userTransactionRepository;

    public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto request) {
        return userRepository.updateUserBalance(request.getUserId(), request.getAmount())
                .filter(Boolean::booleanValue)
                .map(b -> EntityDtoUtil.toEntity(request))
                .flatMap(userTransactionRepository::save)
                .map(ut -> EntityDtoUtil.toDto(request, TransactionStatus.APPROVED))
                .defaultIfEmpty(EntityDtoUtil.toDto(request, TransactionStatus.DECLINED));
    }
}
