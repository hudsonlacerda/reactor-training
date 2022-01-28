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

import digital.osf.reactortraining.model.Store;
import digital.osf.reactortraining.request.StoreRequestBody;
import digital.osf.reactortraining.service.StoreService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @GetMapping
    public Flux<Store> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return this.storeService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Store>> findById(@PathVariable("id") Integer id) {
        return this.storeService.findById(id).map((store) -> ResponseEntity.ok().body(store));
    }

    @PostMapping
    public Mono<ResponseEntity<Store>> create(@RequestBody @Valid StoreRequestBody requestBody) {
        return this.storeService.create(requestBody).map((store) -> ResponseEntity.status(HttpStatus.CREATED).body(store));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Store>> update(@PathVariable("id") Integer id, @RequestBody @Valid StoreRequestBody requestBody) {
        return this.storeService.update(id, requestBody).map((store) -> ResponseEntity.ok().body(store));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        return this.storeService.delete(id).map((v) -> ResponseEntity.noContent().build());
    }

}
