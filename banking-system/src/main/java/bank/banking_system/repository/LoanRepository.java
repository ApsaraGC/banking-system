package bank.banking_system.repository;

import bank.banking_system.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, String> {
    List<Loan> findByAccount_AccountNumber(String accNo);
}
