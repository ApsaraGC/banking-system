package bank.banking_system.service;

import bank.banking_system.model.Account;
import bank.banking_system.model.Loan;
import bank.banking_system.repository.AccountRepository;
import bank.banking_system.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
    private final LoanRepository loanRepository;
    private final AccountRepository accountRepository;
    @Override
    public Loan applyLoan(String accountNumber, Double amount, Integer termMonths) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        Loan loan=new Loan();
        loan.setLoanId(UUID.randomUUID().toString());
        loan.setAccount(account);
        loan.setAmount(amount);
        loan.setTermMonths(termMonths);
        loan.setPaidAmount(0.0);
        loan.setStatus("Pending");
        return loanRepository.save(loan);
    }

    @Override
    public void payInstallment(String loanId, Double amount) {
        Loan loan=loanRepository.findById(loanId).orElseThrow();
        if (loan.getPaidAmount() >=loan.getAmount()) loan.setStatus("Paid");
        loanRepository.save(loan);

    }

    @Override
    public List<Loan> getLoansByAccount(String accountNumber) {
        return loanRepository.findByAccount_AccountNumber(accountNumber);
    }

    @Override
    public Loan getLoanById(String loanId) {
        return loanRepository.findById(loanId).orElseThrow();
    }
}
