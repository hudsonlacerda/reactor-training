package digital.osf.reactortraining.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Stock;

public interface StockRepository extends R2dbcRepository<Stock, Integer> {

}