package digital.osf.reactortraining.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Product;
import reactor.core.publisher.Flux;

public interface ProductRepository extends R2dbcRepository<Product, Integer> {

    Flux<Product> findAllBy(Pageable pageable);

}
