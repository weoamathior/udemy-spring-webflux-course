package irish.bla.orderservice.controller;

import irish.bla.orderservice.dto.PurchaseOrderRequestDto;
import irish.bla.orderservice.dto.PurchaseOrderResponseDto;
import irish.bla.orderservice.service.OrderFulfillmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {
    private final OrderFulfillmentService orderFulfillmentService;

    @PostMapping
    public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> req) {
        return orderFulfillmentService.processOrder(req);
    }

}
