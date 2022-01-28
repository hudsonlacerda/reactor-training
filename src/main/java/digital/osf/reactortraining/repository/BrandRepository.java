package digital.osf.reactortraining.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import digital.osf.reactortraining.model.Brand;

public interface BrandRepository extends R2dbcRepository<Brand, Integer> {

}