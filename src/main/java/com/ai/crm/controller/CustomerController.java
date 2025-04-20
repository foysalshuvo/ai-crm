package com.ai.crm.controller;

import com.ai.crm.interfaces.CustomerService;
import com.ai.crm.model.Customer;
import com.ai.crm.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*") // optional if frontend is involved
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // ✅ Create Customer
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        Customer saved = customerService.createCustomer(customer);
        return ResponseBuilder.created(saved, "Customer created successfully");
    }

    // ✅ Get All Customers
    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseBuilder.success(customers, "All customers fetched successfully");
    }

    // ✅ Get Customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> ResponseBuilder.success(customer, "Customer fetched successfully"))
                .orElseGet(() -> ResponseBuilder.error(HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // ✅ Update Customer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer)
                .map(customer -> ResponseBuilder.success(customer, "Customer updated successfully"))
                .orElseGet(() -> ResponseBuilder.error(org.springframework.http.HttpStatus.NOT_FOUND, "Customer not found"));
    }

    // ✅ Delete Customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ResponseBuilder.success(null, "Customer deleted successfully");
        } else {
            return ResponseBuilder.error(org.springframework.http.HttpStatus.NOT_FOUND, "Customer not found");
        }
    }
}
