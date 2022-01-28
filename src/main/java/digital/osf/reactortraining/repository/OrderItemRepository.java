package digital.osf.reactortraining.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.OrderItem;

public interface OrderItemRepository extends R2dbcRepository<OrderItem, Integer> {

}