package irish.bla.orderservice.controller;

import irish.bla.orderservice.dto.PurchaseOrderRequestDto;
import irish.bla.orderservice.dto.PurchaseOrderResponseDto;
import irish.bla.orderservice.service.OrderFulfillmentService;
import irish.bla.orderservice.service.OrderQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
curl -XPOST -H "Content-type: application/json" -d '{"productId":"65a55a2b5f201a698c855a16", "userId":1}' localhost:8080/order
 */
@RestController
@Slf4j
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {
    private final OrderFulfillmentService orderFulfillmentService;
    private final OrderQueryService orderQueryService;

    @PostMapping
    public Mono<PurchaseOrderResponseDto> order(@RequestBody Mono<PurchaseOrderRequestDto> req) {
        return orderFulfillmentService.processOrder(req);
    }

    @GetMapping("user/{userId}")
    public Flux<PurchaseOrderResponseDto> getOrdersByUserID(@PathVariable int userId) {
        return orderQueryService.getProductsByUserID(userId);
    }

}
