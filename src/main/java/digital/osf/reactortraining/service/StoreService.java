package digital.osf.reactortraining.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import digital.osf.reactortraining.exception.BadRequestException;
import digital.osf.reactortraining.model.Store;
import digital.osf.reactortraining.repository.StoreRepository;
import digital.osf.reactortraining.request.StoreRequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Flux<Store> findAll(Pageable pageable) {
        return this.storeRepository.findAllBy(pageable);
    }

    public Mono<Store> findById(Integer id) {
        return this.storeRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException("store not found")));
    }

    public Mono<Store> create(@Valid StoreRequestBody requestBody) {
        Store store = requestBody.convert();

        return storeRepository.save(store);
    }

    public Mono<Store> update(Integer id, @Valid StoreRequestBody requestBody) {
        return this.findById(id)
                .flatMap(this.storeRepository::save);
    }

    public Mono<Void> delete(Integer id) {
        return this.findById(id).flatMap(this.storeRepository::delete);
    }

}