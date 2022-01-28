package digital.osf.reactortraining.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import digital.osf.reactortraining.exception.BadRequestException;
import digital.osf.reactortraining.model.Brand;
import digital.osf.reactortraining.model.Category;
import digital.osf.reactortraining.model.Product;
import digital.osf.reactortraining.repository.BrandRepository;
import digital.osf.reactortraining.repository.CategoryRepository;
import digital.osf.reactortraining.repository.ProductRepository;
import digital.osf.reactortraining.request.ProductRequestBody;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    public Flux<Product> findAll(Pageable pageable) {
        return this.productRepository.findAllBy(pageable);
    }

    public Mono<Product> findById(Integer id) {
        return this.productRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException("product id not found")));

    }

    public Mono<Product> create(ProductRequestBody requestBody) {
        return this.resolveCategory(requestBody.convert(), requestBody.getCategory())
                .flatMap((p) -> this.resolveBrand(p, requestBody.getBrand()))
                .flatMap(this.productRepository::save);
    }

    public Mono<Product> update(Integer id, @Valid ProductRequestBody requestBody) {
        this.findById(id);

        return this.resolveCategory(requestBody.convert(), requestBody.getCategory())
                .flatMap((p) -> this.resolveBrand(p, requestBody.getBrand()))
                .map((p) -> p.withId(id))
                .flatMap(this.productRepository::save);
    }

    public Mono<Void> delete(Integer id) {
        return this.findById(id).flatMap(this.productRepository::delete);
    }

    private Mono<Product> resolveCategory(Product product, Integer categoryId) {
        Mono<Category> category = this.categoryRepository.findById(categoryId)
                .switchIfEmpty(Mono.error(new BadRequestException("category not exists")));

        return category.map((foundCategory) -> product.withCategory(foundCategory.getId()));
    }

    private Mono<Product> resolveBrand(Product product, Integer brandId) {
        Mono<Brand> brand = this.brandRepository.findById(brandId)
                .switchIfEmpty(Mono.error(new BadRequestException("brand not exists")));

        return brand.map((foundBrand) -> product.withBrand(foundBrand.getId()));
    }

}
