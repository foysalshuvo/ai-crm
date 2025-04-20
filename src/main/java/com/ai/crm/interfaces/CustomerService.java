package com.ai.crm.interfaces;

import com.ai.crm.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(Long id);
    Optional<Customer> updateCustomer(Long id, Customer customer);
    boolean deleteCustomer(Long id);
}
