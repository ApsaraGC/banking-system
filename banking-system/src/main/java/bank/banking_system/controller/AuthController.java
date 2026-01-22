package bank.banking_system.controller;

import bank.banking_system.model.Customer;
import bank.banking_system.repository.CustomerRepository;
import bank.banking_system.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    //register a new customer
    @PostMapping("/register")
    public Customer register(@RequestBody Customer customer){
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole("ROLE_CUSTOMER");//default role
        return customerRepository.save(customer);

    }
    //login(http basic will handle auth automatically)
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String, String> request){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.get("username"),
                        request.get("password")
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Customer customer=customerRepository
                .findByUsername(request.get("username"))
                .orElseThrow();

        String token =jwtUtil.generateToken(
                customer.getUsername(),
                customer.getRole()
        );
        return Map.of(
                "token", token,
                "role", customer.getRole()
        );

    }
}
