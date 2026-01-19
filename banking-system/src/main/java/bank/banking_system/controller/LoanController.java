package bank.banking_system.controller;

import bank.banking_system.model.Loan;
import bank.banking_system.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    //apply for new loan
    @PostMapping("/apply")
    public Loan applyLoan(@RequestBody Map<String, Object> request){
        String accountNumber=  request.get("accountNumber").toString();
        Double amount = Double.valueOf(request.get("amount").toString());
        Integer termMonths =Integer.valueOf(request.get("termMonths").toString());
        return loanService.applyLoan(accountNumber, amount, termMonths);
    }
    //pay installment for loan
    @PostMapping("/pay/{loanId}")
    public String payInstallment(@PathVariable String loanId, @RequestBody Map<String, Object>request) {

        Double amount = Double.valueOf(request.get("amount").toString());
        loanService.payInstallment(loanId,amount);
        return "Installment Paid Successfully";
    }
    //get a specific loan by ID
    @GetMapping("/{loandId}")
    public Loan getLoanById(@PathVariable String loandId){
        return loanService.getLoanById(loandId);
    }
}
