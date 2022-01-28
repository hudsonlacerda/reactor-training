package digital.osf.reactortraining.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Category;

public interface CategoryRepository extends R2dbcRepository<Category, Integer> {

}