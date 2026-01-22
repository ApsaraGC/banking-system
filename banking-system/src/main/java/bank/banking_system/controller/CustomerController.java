package bank.banking_system.controller;

import bank.banking_system.model.Customer;
import bank.banking_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        if (customer.getAccounts() == null) {
            customer.setAccounts(new ArrayList<>());
        }
        return customerService.addCustomer(customer);
    }

    //update customer details
    @PutMapping("/update/{customerId}")
    public Customer updateCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
        return customerService.updateCustomer(customerId, customer);

    }

    //get customer by id
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable String customerId) {
        return customerService.getCustomerById(customerId);
    }

    //get all customers
    @GetMapping("/all")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }
}
