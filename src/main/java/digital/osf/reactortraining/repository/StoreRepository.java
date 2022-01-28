package digital.osf.reactortraining.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Store;
import reactor.core.publisher.Flux;

public interface StoreRepository extends R2dbcRepository<Store, Integer> {

    Flux<Store> findAllBy(Pageable pageable);

}