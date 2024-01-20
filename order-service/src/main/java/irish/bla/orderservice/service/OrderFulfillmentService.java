package irish.bla.orderservice.service;

import irish.bla.orderservice.client.ProductClient;
import irish.bla.orderservice.client.UserClient;
import irish.bla.orderservice.dto.PurchaseOrderRequestDto;
import irish.bla.orderservice.dto.PurchaseOrderResponseDto;
import irish.bla.orderservice.dto.RequestContext;
import irish.bla.orderservice.repository.PurchaseOrderRepository;
import irish.bla.orderservice.util.EntityDtoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderFulfillmentService {

    private final ProductClient productClient;
    private final UserClient userClient;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> request) {
        return request.map(RequestContext::new)
                .flatMap(this::productRequestResponse)
                .doOnNext(EntityDtoUtil::setTransactionRequestDto)
                .flatMap(this::userRequestResponse)
                .map(EntityDtoUtil::getPurchaseOrder)
                .map(purchaseOrderRepository::save) // blocking!
                .map(EntityDtoUtil::getPurchaseOrderResponseDto)
                .subscribeOn(Schedulers.boundedElastic()); // magic TODO


    }

    private Mono<RequestContext> userRequestResponse(RequestContext context) {
        return this.userClient.authorizeTransaction(context.getTransactionRequestDto())
                .doOnNext(context::setTransactionResponseDto)
                .thenReturn(context);
    }

    private Mono<RequestContext> productRequestResponse(RequestContext context) {
        return this.productClient.getProductById(context.getPurchaseOrderRequestDto().getProductId())
                .doOnNext(context::setProductDto)
                .thenReturn(context);
    }

}
