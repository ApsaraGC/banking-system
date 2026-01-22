package bank.banking_system.service;

import bank.banking_system.dto.BankStatementResponse;
import bank.banking_system.model.Account;
import bank.banking_system.model.Transaction;

import java.util.List;

public interface BankService {
    Account createAccount(Account account);
    Account deposit(String accNo, Double amount);
    Account withdraw(String accNo, Double amount);
    void transfer(String from, String to, Double amount);
    Double checkBalance(String accountNumber);
    BankStatementResponse getStatement(String accountNumber);
   // Account getAccount(String accountNumber);
//List<Transaction> getTransactions(String accountNumber);
}
