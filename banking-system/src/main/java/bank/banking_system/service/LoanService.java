package bank.banking_system.service;

import bank.banking_system.model.Loan;

import java.util.List;

public interface LoanService {
    Loan applyLoan(String accountNumber, Double amount, Integer termMonths);
    void payInstallment(String loanId, Double amount);
    List<Loan>getLoansByAccount(String accountNumber);
    Loan getLoanById(String loanId);
}
