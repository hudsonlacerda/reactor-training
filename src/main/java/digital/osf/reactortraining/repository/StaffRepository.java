package digital.osf.reactortraining.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Staff;
import reactor.core.publisher.Flux;

public interface StaffRepository extends R2dbcRepository<Staff, Integer> {

    Flux<Staff> findAllBy(Pageable pageable);

}