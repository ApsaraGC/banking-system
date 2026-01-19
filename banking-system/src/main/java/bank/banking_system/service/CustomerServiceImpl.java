package bank.banking_system.service;

import bank.banking_system.model.Customer;
import bank.banking_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String customerId, Customer customer) {
        Customer existing=customerRepository.findById(customerId).orElseThrow();
        existing.setName(customer.getName());
        existing.setEmail(customer.getEmail());
        existing.setPhone(customer.getPhone());
        return customerRepository.save(existing);
    }

    @Override
    public Customer getCustomerById(String customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
