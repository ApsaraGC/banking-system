package bank.banking_system.service;

import bank.banking_system.model.Account;
import bank.banking_system.model.Transaction;
import bank.banking_system.repository.AccountRepository;
import bank.banking_system.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BankServiceImpl implements BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public Account createAccount(Account account){
        account.setBalance(0.0);
        return accountRepository.save(account);

    }
    @Override
    public Account deposit(String accountNumber, Double amount){
        Account account=getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        saveTransaction(account, "Deposit", amount);
        return accountRepository.save(account);


    }
    @Override
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
    @Override
    public void transfer(String fromAccount, String toAccount, Double amount){
        Account sender=getAccount(fromAccount);
        Account receiver=getAccount(toAccount);

        if(sender.getBalance()< amount){
            throw new RuntimeException("Insufficient balance");

        }
        sender.setBalance(sender.getBalance()-amount);
        receiver.setBalance(receiver.getBalance() + amount);

        saveTransaction(sender, "Transfer_out", amount);
        saveTransaction(receiver, "Transfer_in", amount);

        accountRepository.save(sender);
        accountRepository.save(receiver);

    }


    @Override
    public Double checkBalance(String accountNumber) {
        return getAccount(accountNumber).getBalance();
    }

    @Override
    public Account getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not fount"));    }

    @Override
    public List<Transaction> getTransactions(String accountNumber) {
        return transactionRepository.findByAccountAccountNumber(accountNumber);
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
