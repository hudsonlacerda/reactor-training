package digital.osf.reactortraining.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import digital.osf.reactortraining.exception.BadRequestException;
import digital.osf.reactortraining.model.Customer;
import digital.osf.reactortraining.repository.CustomerRepository;
import digital.osf.reactortraining.request.CustomerRequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Flux<Customer> findAll(Pageable pageable) {
        return this.customerRepository.findAllBy(pageable);
    }

    public Mono<Customer> findById(Integer id) {
        return this.customerRepository.findById(id)
                .switchIfEmpty(Mono.error(new BadRequestException("customer not found")));
    }

    public Mono<Customer> create(@Valid CustomerRequestBody requestBody) {
        Customer customer = requestBody.convert();
        return this.customerRepository.save(customer);
    }

    public Mono<Customer> update(Integer id, @Valid CustomerRequestBody requestBody) {
        return this.findById(id)
                .map((c) -> requestBody.convert().withId(c.getId()))
                .flatMap(this.customerRepository::save);
    }

    public Mono<Void> delete(Integer id) {
        return this.findById(id).flatMap(this.customerRepository::delete);
    }
}
