package irish.bla.orderservice.service;

import irish.bla.orderservice.dto.PurchaseOrderResponseDto;
import irish.bla.orderservice.repository.PurchaseOrderRepository;
import irish.bla.orderservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderQueryService {

    private final PurchaseOrderRepository orderRepository;

    public Flux<PurchaseOrderResponseDto> getProductsByUserID(int userId) {
        return Flux.fromStream(() -> orderRepository.findByUserId(userId).stream())
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic());
    }

}
