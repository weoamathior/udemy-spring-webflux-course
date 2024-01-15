package irish.bla.productservice.controller;

import irish.bla.productservice.dto.ProductDto;
import irish.bla.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
  curl localhost:8091/product/all
  curl -XPOST -H "Content-type: application/json" -d '{"description":"foo","price":10}' localhost:8091/product
  curl localhost:8091/product/65a54d35ad858a27cf0d8eac
  curl -XPUT -H "Content-type: application/json" -d '{"description":"foo","price":20}' localhost:8091/product/65a54d35ad858a27cf0d8eac
  curl localhost:8091/product/all
  curl -XDELETE localhost:8091/product/65a54d35ad858a27cf0d8eac

 */

@Slf4j
@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("all")
    public Flux<ProductDto> all() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> body) {
        return this.productService.insertProduct(body);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> body) {
        return productService.updateProduct(id, body)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
