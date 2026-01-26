package bank.banking_system.service;

import bank.banking_system.model.Transaction;
import bank.banking_system.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    //get/api/transactions/account/{accountNumber}
    public List<Transaction>getByAccount(String accountNumber){
        return transactionRepository
                .findByAccount_AccountNumber(accountNumber);
    }
    //get/api/transactions/customer/{customerId}
    public List<Transaction> getByCustomer(String customerId){
        return transactionRepository
                .findByAccount_Customer_CustomerIdOrderByTransactionTimeDesc(customerId);
    }
    //get/api/transaction/recent?limit=10
    public List<Transaction>getRecent(int limit){
        return transactionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Transaction::getTransactionTime).reversed())
                .limit(limit)
                .toList();
    }
}
