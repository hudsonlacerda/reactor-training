package digital.osf.reactortraining.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import digital.osf.reactortraining.model.Staff;
import digital.osf.reactortraining.repository.StaffRepository;
import digital.osf.reactortraining.request.StaffRequestBody;
import digital.osf.reactortraining.util.StaffUtil;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaffResourceIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private StaffRepository StaffRepository;

    @Test
    public void findAll_ReturnPagebleStaffs_WhenSuccessful() {
        this.StaffRepository
                .saveAll(StaffUtil.listStaffToPersist())
                .map((savedStaff) -> this.webTestClient
                        .get()
                        .uri("/Staffs")
                        .exchange()
                        .expectStatus().isOk()
                        .expectBodyList(Staff.class));
    }

    @Test
    public void findById_ReturnStaffFromSpecificId_WhenSuccessful() {
        this.StaffRepository
                .save(StaffUtil.staffToPersist())
                .map((savedStaff) -> this.webTestClient
                        .get()
                        .uri("/Staffs/{id}", savedStaff.getId())
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody(Staff.class)
                        .isEqualTo(savedStaff));
    }

    @Test
    public void create_PersistAndReturnNewStaff_WhenSuccessful() {
        this.webTestClient
                .post()
                .uri("/staffs")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(StaffUtil.staffRequestBody()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Staff.class);
    }

    @Test
    public void update_UpdateAndReturnExistingStaff_WhenSuccessful() {
        StaffRequestBody requestBody = StaffUtil.staffRequestBody();

        this.StaffRepository
                .save(StaffUtil.staffToPersist())
                .map((savedStaff) -> this.webTestClient
                        .put()
                        .uri("/Staffs/{id}", savedStaff.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(requestBody))
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(Staff.class)
                        .isEqualTo(requestBody.convert().withId(savedStaff.getId())));
    }

    @Test
    public void delete_DeleteStaffById_WhenSuccessful() {
        this.StaffRepository
                .save(StaffUtil.staffToPersist())
                .map((savedStaff) -> this.webTestClient
                        .delete()
                        .uri("/Staffs/{id}", savedStaff.getId())
                        .exchange()
                        .expectStatus().isNoContent());
    }

}