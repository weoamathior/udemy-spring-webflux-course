package irish.bla.orderservice;

import irish.bla.orderservice.client.ProductClient;
import irish.bla.orderservice.client.UserClient;
import irish.bla.orderservice.dto.ProductDto;
import irish.bla.orderservice.dto.PurchaseOrderRequestDto;
import irish.bla.orderservice.dto.PurchaseOrderResponseDto;
import irish.bla.orderservice.dto.UserDto;
import irish.bla.orderservice.service.OrderFulfillmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	ProductClient productClient;

	@Autowired
	UserClient userClient;

	@Autowired
	OrderFulfillmentService orderFulfillmentService;

	@Test
	void contextLoads() {

		Flux<PurchaseOrderResponseDto> flux = Flux.zip(productClient.getAllProducts(), userClient.getAllUsers())
				.map(t -> buildDto(t.getT1(), t.getT2()))
				.flatMap(dto -> orderFulfillmentService.processOrder(Mono.just(dto)))
				.doOnNext(System.out::println);
		StepVerifier.create(flux)
				.expectNextCount(4)
				.verifyComplete();

	}

	private PurchaseOrderRequestDto buildDto(ProductDto productDto, UserDto userDto) {
		return PurchaseOrderRequestDto.builder()
				.productId(productDto.getId())
				.userId(userDto.getId())
				.build();
	}

}
