package bank.banking_system.controller;

import bank.banking_system.model.Customer;
import bank.banking_system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    //register a new customer
    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_CUSTOMER");//default role
        return customerRepository.save(customer);

    }
    //login(http basic will handle auth automatically)
    @PostMapping("/login")
    public String login(){
        return "Login successful";//actual authentication handled by spring security
    }
}
