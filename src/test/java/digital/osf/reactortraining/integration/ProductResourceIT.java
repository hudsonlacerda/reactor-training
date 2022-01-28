package digital.osf.reactortraining.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import digital.osf.reactortraining.model.Product;
import digital.osf.reactortraining.repository.ProductRepository;
import digital.osf.reactortraining.request.ProductRequestBody;
import digital.osf.reactortraining.util.ProductUtil;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findAll_ReturnPagebleProducts_WhenSuccessful() {
        this.productRepository
                .saveAll(ProductUtil.listProductToPersist())
                .map((savedProduct) -> this.webTestClient
                        .get()
                        .uri("/products")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Product.class));
    }

    @Test
    public void findById_ReturnProductFromSpecificId_WhenSuccessful() {
        this.productRepository
                .save(ProductUtil.productToPersist())
                .map((savedProduct) -> this.webTestClient
                        .get()
                        .uri("/products/{id}", savedProduct.getId())
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(Product.class)
                        .isEqualTo(savedProduct));
    }

    @Test
    public void create_PersistAndReturnNewProduct_WhenSuccessful() {
        this.webTestClient
                .post()
                .uri("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(ProductUtil.productRequestBody()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Product.class);
    }

    @Test
    public void update_UpdateAndReturnExistingProduct_WhenSuccessful() {
        ProductRequestBody requestBody = ProductUtil.productRequestBody();

        this.productRepository
                .save(ProductUtil.productToPersist())
                .map((savedProduct) -> this.webTestClient
                        .put()
                        .uri("/products/{id}", savedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(requestBody))
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(Product.class)
                        .isEqualTo(requestBody.convert().withId(savedProduct.getId())));
    }

    @Test
    public void delete_DeleteProductById_WhenSuccessful() {
        this.productRepository
                .save(ProductUtil.productToPersist())
                .map((savedProduct) -> this.webTestClient
                        .delete()
                        .uri("/products/{id}", savedProduct.getId())
                        .exchange()
                        .expectStatus().isNoContent());
    }

}