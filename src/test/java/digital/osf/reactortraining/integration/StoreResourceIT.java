package digital.osf.reactortraining.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import digital.osf.reactortraining.model.Store;
import digital.osf.reactortraining.repository.StoreRepository;
import digital.osf.reactortraining.request.StoreRequestBody;
import digital.osf.reactortraining.util.StoreUtil;
import reactor.core.publisher.Mono;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreResourceIT {
        @Autowired
        private WebTestClient webTestClient;
        @Autowired
        private StoreRepository storeRepository;

        @Test
        public void findAll_ReturnPagebleStores_WhenSuccessful() {
                this.storeRepository
                                .saveAll(StoreUtil.listStoreToPersist())
                                .map((savedStore) -> this.webTestClient
                                                .get()
                                                .uri("/Stores")
                                                .exchange()
                                                .expectStatus().isOk()
                                                .expectBodyList(Store.class));
        }

        @Test
        public void findById_ReturnStoreFromSpecificId_WhenSuccessful() {
                this.storeRepository
                                .save(StoreUtil.storeToPersist())
                                .map((savedStore) -> this.webTestClient
                                                .get()
                                                .uri("/Stores/{id}", savedStore.getId())
                                                .exchange()
                                                .expectStatus()
                                                .isOk()
                                                .expectBody(Store.class)
                                                .isEqualTo(savedStore));
        }

        @Test
        public void create_PersistAndReturnNewStore_WhenSuccessful() {
                this.webTestClient
                                .post()
                                .uri("/stores")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(StoreUtil.storeRequestBody()))
                                .exchange()
                                .expectStatus().isCreated()
                                .expectBody(Store.class);
        }

        @Test
        public void update_UpdateAndReturnExistingStore_WhenSuccessful() {
                StoreRequestBody requestBody = StoreUtil.storeRequestBody();

                Mono<Store> monoSavedStore = this.storeRepository.save(StoreUtil.storeToPersist());
                monoSavedStore.map((savedStore) -> this.webTestClient
                                .put()
                                .uri("/stores/{id}", savedStore.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromValue(requestBody))
                                .exchange()
                                .expectStatus().isOk()
                                .expectBody(Store.class)
                                .isEqualTo(requestBody.convert().withId(savedStore.getId())));
        }

        @Test
        public void delete_DeleteStoreById_WhenSuccessful() {
                this.storeRepository
                                .save(StoreUtil.storeToPersist())
                                .map((savedStore) -> this.webTestClient
                                                .delete()
                                                .uri("/Stores/{id}", savedStore.getId())
                                                .exchange()
                                                .expectStatus().isNoContent());
        }

}