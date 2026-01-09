package bank.banking_system.service;

import bank.banking_system.model.Account;
import bank.banking_system.model.Transaction;
import bank.banking_system.repository.AccountRepository;
import bank.banking_system.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;


    public Account createAccount(Account account){
        account.setBalance(0.0);
        return accountRepository.save(account);

    }
    public Account deposit(String accNo, Double amount){
        Account account= accountRepository.findByAccountNumber(accNo)
                .orElseThrow(()->new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        saveTransaction(account, "Deposit", amount);
        return accountRepository.save(account);


    }
    public Account withdraw(String accNo, Double amount){
        Account account=accountRepository.findByAccountNumber(accNo)
                .orElseThrow(()->new RuntimeException("Account not found"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");

        }
        account.setBalance(account.getBalance() - amount);
        saveTransaction(account, "Withdraw", amount);
        return accountRepository.save(account);

    }
    public void transfer(String from, String to, Double amount){
        withdraw(from, amount);
        deposit(to, amount);
    }

    private void saveTransaction(Account account, String type, Double amount){
        Transaction transaction=new Transaction();
        transaction.setAccount(account);
        transaction.setType(type);
        transaction.setAmount(amount);
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

}
