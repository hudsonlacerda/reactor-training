package digital.osf.reactortraining.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import digital.osf.reactortraining.model.Customer;
import digital.osf.reactortraining.repository.CustomerRepository;
import digital.osf.reactortraining.request.CustomerRequestBody;
import digital.osf.reactortraining.util.CustomerUtil;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAll_ReturnPagebleCustomers_WhenSuccessful() {
        this.customerRepository
                .saveAll(CustomerUtil.listCustomersToPersist())
                .map((savedCustomer) -> this.webTestClient
                        .get()
                        .uri("/customers")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Customer.class));
    }

    @Test
    public void findById_ReturnCustomerFromSpecificId_WhenSuccessful() {
        this.customerRepository
                .save(CustomerUtil.customerToPersist())
                .map((savedCustomer) -> this.webTestClient
                        .get()
                        .uri("/customers/{id}", savedCustomer.getId())
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(Customer.class)
                        .isEqualTo(savedCustomer));
    }

    @Test
    public void create_PersistAndReturnNewCustomer_WhenSuccessful() {
        this.webTestClient
                .post()
                .uri("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(CustomerUtil.customerRequestBody()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Customer.class);
    }

    @Test
    public void update_UpdateAndReturnExistingCustomer_WhenSuccessful() {
        CustomerRequestBody requestBody = CustomerUtil.customerRequestBody();
        requestBody.setPhone("8888-8888");
        requestBody.setEmail("Customer@osf.digital");

        this.customerRepository
                .save(CustomerUtil.customerToPersist())
                .map((savedCustomer) -> this.webTestClient
                        .put()
                        .uri("/customers/{id}", savedCustomer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(requestBody))
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(Customer.class)
                        .isEqualTo(requestBody.convert().withId(savedCustomer.getId())));
    }

    @Test
    public void delete_DeleteCustomerById_WhenSuccessful() {
        this.customerRepository
                .save(CustomerUtil.customerToPersist())
                .map((savedCustomer) -> this.webTestClient
                        .delete()
                        .uri("/customers/{id}", savedCustomer.getId())
                        .exchange()
                        .expectStatus().isNoContent());
    }

}