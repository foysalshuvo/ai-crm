package com.ai.crm.service;

import com.ai.crm.interfaces.CustomerService;
import com.ai.crm.model.Customer;
import com.ai.crm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // ✅ Create Customer
    @Override
    public Customer createCustomer(Customer customer) {
        // Perform any additional validation, if necessary
        return customerRepository.save(customer);
    }

    // ✅ Get All Customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // ✅ Get Customer by ID
    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // ✅ Update Customer
    @Override
    public Optional<Customer> updateCustomer(Long id, Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            // Update the existing customer's fields
            Customer customer = existingCustomer.get();
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPhone(updatedCustomer.getPhone());
            customer.setBankAccount(updatedCustomer.getBankAccount());

            // Save the updated customer
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty(); // Customer not found
    }

    // ✅ Delete Customer
    @Override
    public boolean deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            customerRepository.delete(customer.get());
            return true; // Customer deleted
        }
        return false; // Customer not found
    }
}
