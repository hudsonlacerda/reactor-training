package digital.osf.reactortraining.service;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import digital.osf.reactortraining.exception.BadRequestException;
import digital.osf.reactortraining.model.Staff;
import digital.osf.reactortraining.repository.StaffRepository;
import digital.osf.reactortraining.request.StaffRequestBody;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;
    private final StoreService storeService;

    public Flux<Staff> findAll(Pageable pageable) {
        return this.staffRepository.findAllBy(pageable);
    }

    public Mono<Staff> findById(Integer id) {
        return this.staffRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException("staff not found")));
    }

    public Mono<Staff> create(@Valid StaffRequestBody requestBody) {
        return this.resolveStore(requestBody.convert(), requestBody.getStore())
                .flatMap((staff) -> this.resolveManager(staff, requestBody.getManager()))
                .flatMap(this.staffRepository::save);
    }

    public Mono<Staff> update(Integer id, @Valid StaffRequestBody requestBody) {
        return this.findById(id)
                .flatMap((staff) -> this.resolveStore(requestBody.convert(), requestBody.getStore()))
                .flatMap((staff) -> this.resolveManager(staff, requestBody.getManager()))
                .map((staffToUpdate) -> staffToUpdate.withId(id))
                .flatMap(this.staffRepository::save);
    }

    public Mono<Void> delete(Integer id) {
        return this.findById(id)
                .map((staff) -> {
                    if (!staff.getStaffs().isEmpty()) {
                        return Mono.error(new BadRequestException(
                                "This manager is responsible for some employees. Please unlink before performing the delete operation."));
                    } else {
                        return this.staffRepository.delete(staff);
                    }
                }).then();

    }

    private Mono<Staff> resolveStore(Staff staff, Integer storeId) {
        return this.storeService.findById(storeId)
                .map(sotre -> staff.withStore(sotre.getId()));
    }

    private Mono<Staff> resolveManager(Staff staff, Integer managerId) {
        return this.findById(managerId)
                .map(manager -> staff.withManager(manager.getId()));
    }
}
