package digital.osf.reactortraining.controller;

import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digital.osf.reactortraining.model.Staff;
import digital.osf.reactortraining.request.StaffRequestBody;
import digital.osf.reactortraining.service.StaffService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/staffs")
@RequiredArgsConstructor
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public Flux<Staff> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        return this.staffService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Staff>> findById(@PathVariable("id") Integer id) {
        return this.staffService.findById(id).map((staff) -> ResponseEntity.ok().body(staff));
    }

    @PostMapping
    public Mono<ResponseEntity<Staff>> create(@RequestBody @Valid StaffRequestBody requestBody) {
        return this.staffService.create(requestBody).map((staff) -> ResponseEntity.status(HttpStatus.CREATED).body(staff));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Staff>> update(@PathVariable("id") Integer id, @RequestBody @Valid StaffRequestBody requestBody) {
        return this.staffService.update(id, requestBody).map((staff) -> ResponseEntity.ok().body(staff));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") Integer id) {
        return this.staffService.delete(id).map((v) -> ResponseEntity.noContent().build());
    }

}
