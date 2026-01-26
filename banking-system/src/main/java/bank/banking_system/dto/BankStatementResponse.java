package bank.banking_system.dto;


import bank.banking_system.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BankStatementResponse {

    private String accountNumber;
    private Double balance;
    private List<Transaction> transactions;
}
