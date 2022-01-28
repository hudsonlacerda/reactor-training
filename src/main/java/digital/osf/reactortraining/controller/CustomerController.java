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

import digital.osf.reactortraining.model.Customer;
import digital.osf.reactortraining.request.CustomerRequestBody;
import digital.osf.reactortraining.service.CustomerService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public Flux<Customer> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return this.customerService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Customer>> findById(@PathVariable("id") Integer id) {
        return this.customerService.findById(id).map(customer -> ResponseEntity.ok().body(customer));
    }

    @PostMapping
    public Mono<ResponseEntity<Customer>> create(@RequestBody @Valid CustomerRequestBody requestBody) {
        return this.customerService.create(requestBody)
                .map(customer -> ResponseEntity.status(HttpStatus.CREATED).body(customer));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Customer>> update(@PathVariable("id") Integer id,
            @RequestBody @Valid CustomerRequestBody requestBody) {
        return this.customerService.update(id, requestBody).map((customer) -> ResponseEntity.ok().body(customer));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        return this.customerService.delete(id).map((nothing) -> ResponseEntity.noContent().build());
    }

}
