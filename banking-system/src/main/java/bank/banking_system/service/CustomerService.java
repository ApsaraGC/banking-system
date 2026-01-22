package bank.banking_system.service;

import bank.banking_system.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    Customer updateCustomer(String customerId, Customer customer);
    Customer getCustomerById(String customerId);
    List<Customer>getAllCustomers();
}
