package bank.banking_system.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountHolderName;
    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;
    private Double balance;

    @ManyToOne
    private Customer customer;
}
