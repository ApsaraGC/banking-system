package bank.banking_system.controller;

import bank.banking_system.model.Account;
import bank.banking_system.service.BankService;
import bank.banking_system.service.BankServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name="Bank APIs", description = "Banking operations")
@RestController
@RequestMapping("/api/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("/create")
    public Account createAccount(@RequestBody Account account){
        return bankService.createAccount(account);
    }
//Deposit using JSON body
    @PostMapping("/deposit")
    public Account deposit(@RequestBody Map<String, Object> request){
        String accNo=(String) request.get("accNo");
        Double amount= Double.valueOf(request.get("amount").toString());
        return bankService.deposit(accNo, amount);

    }
    @PostMapping("/withdraw")
    public Account withdraw(@RequestBody Map<String, Object> request){
        String accNo=(String) request.get("accNo");
        Double amount= Double.valueOf(request.get("amount").toString());
        return bankService.withdraw(accNo, amount);
    }
    @PostMapping("/transfer")
    public String transfer(@RequestBody Map<String, Object> request){
        String from=(String) request.get("from");
        String to=(String) request.get("to");
        String accNo=(String) request.get("accNo");
        Double amount= Double.valueOf(request.get("amount").toString());
        bankService.transfer(from, to, amount);
        return "Transfer Successful";
    }

    @GetMapping("/balance/{accountNumber}")
    public Double checkBalance(@PathVariable String accountNumber){
        return bankService.checkBalance(accountNumber);
    }
}
