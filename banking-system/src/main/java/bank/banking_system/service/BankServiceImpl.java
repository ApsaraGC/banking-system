package bank.banking_system.service;

import bank.banking_system.dto.BankStatementResponse;
import bank.banking_system.event.TransactionNotificationEvent;
import bank.banking_system.model.Account;
import bank.banking_system.model.Transaction;
import bank.banking_system.notification.NotificationType;
import bank.banking_system.repository.AccountRepository;
import bank.banking_system.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final ApplicationEventPublisher eventPublisher;
    @Override
    public Account createAccount(Account account){
        account.setBalance(0.0);
        return accountRepository.save(account);

    }
    @Override
    public Account deposit(String accNo, Double amount){
        Account account = accountRepository.findByAccountNumber(accNo)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance()+ amount);
        accountRepository.save(account);

        Transaction transaction=new Transaction();
        transaction.setAccount(account);
        transaction.setType("Deposit");
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);

        eventPublisher.publishEvent(
                new TransactionNotificationEvent(
                        account.getCustomer().getCustomerId(),
                        NotificationType.DEPOSIT,
                        "Rs." + amount + "deposited successfully"
                )
        );

        return account;



    }
    @Override
    public Account withdraw(String accNo, Double amount){
        Account account=accountRepository.findByAccountNumber(accNo)
                .orElseThrow(()->new RuntimeException("Account not found"));

        if(account.getBalance()<amount){
            throw new RuntimeException("Insufficient balance");

        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction=new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType("Withdraw");
        transaction.setTransactionTime(LocalDateTime.now());
        transactionRepository.save(transaction);
        return account;

    }
    @Override
    public void transfer(String fromAcc, String toAcc, Double amount){
        withdraw(fromAcc, amount);
        deposit(toAcc, amount);

    }


    @Override
    public Double checkBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    @Override
    public BankStatementResponse getStatement(String accountNumber){
        Account account =accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(()->new RuntimeException("Account not found"));
        List<Transaction> transactions =
                transactionRepository.findByAccount_AccountNumber(accountNumber);
        return new BankStatementResponse(
                account.getAccountNumber(),
                account.getBalance(),
                transactions
        );
    }

}
