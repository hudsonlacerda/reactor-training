package digital.osf.reactortraining.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Customer;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends R2dbcRepository<Customer, Integer> {

    Flux<Customer> findAllBy(Pageable pageable);

}
