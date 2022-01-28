package digital.osf.reactortraining.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Order;

public interface OrderRepository extends R2dbcRepository<Order, Integer> {

}