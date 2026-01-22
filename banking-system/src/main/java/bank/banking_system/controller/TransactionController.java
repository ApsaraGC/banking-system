package bank.banking_system.controller;

import bank.banking_system.model.Transaction;
import bank.banking_system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    //get/api/transactions/account/{accountNumber}
    @GetMapping("/account/{accountNumber}")
    public List<Transaction>byAccount(@PathVariable String accountNumber){
        return transactionService.getByAccount(accountNumber);

    }
    //get/api/transactions/customer/{customerId}
    @GetMapping("/customer/{customerId}")
    public List<Transaction> byCustomer(@PathVariable String customerId){
        return transactionService.getByCustomer(customerId);
    }
    //get/api/transaction/recent?limit=10
    @GetMapping("/recent")
    public List<Transaction> recent(@RequestParam(defaultValue = "10")int limit){
        return transactionService.getRecent(limit);

    }
}
