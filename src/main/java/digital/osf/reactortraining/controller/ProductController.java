package digital.osf.reactortraining.controller;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digital.osf.reactortraining.model.Product;
import digital.osf.reactortraining.request.ProductRequestBody;
import digital.osf.reactortraining.service.ProductService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Flux<Product> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return this.productService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> findById(@PathVariable("id") Integer id) {
        return this.productService.findById(id).map((product) -> ResponseEntity.ok().body(product));
    }

    @PostMapping
    public Mono<ResponseEntity<Product>> create(@RequestBody @Valid ProductRequestBody requestBody) {
        return this.productService.create(requestBody).map((product) -> ResponseEntity.status(HttpStatus.CREATED).body(product));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Product>> update(@PathVariable("id") Integer id, @RequestBody @Valid ProductRequestBody requestBody) {
        return this.productService.update(id, requestBody).map((product) -> ResponseEntity.ok().body(product));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        return this.productService.delete(id).map((v) -> ResponseEntity.noContent().build());
    }

}
