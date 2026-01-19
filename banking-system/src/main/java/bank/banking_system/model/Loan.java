package bank.banking_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    @Id
    private String loanId;
    private Double amount;
    private  Integer termMonths;
    private Double paidAmount;
    private String status;// approved, pending, paid

    @ManyToOne
    private Account account;
}
