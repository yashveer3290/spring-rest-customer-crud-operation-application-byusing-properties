package com.sunglowsys.rest;

import com.sunglowsys.domain.Customer;
import com.sunglowsys.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerResource.class);
    private final CustomerService customerService;

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        LOGGER.debug("REST request to create Customer / {}",customer);
        Customer result = customerService.save(customer);
        return ResponseEntity
                .ok()
                .body(result);

    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> result = customerService.findAll();
        return ResponseEntity.ok().body(result);
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,@PathVariable("id") Long id) {
        Customer result = customerService.update(customer,id);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getById(@PathVariable("id") Long id) {
        Customer result = customerService.findById(id);
        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
         customerService.delete(id);
         return ResponseEntity.noContent().build();
    }

}
