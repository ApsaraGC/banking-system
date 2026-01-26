package bank.banking_system.repository;

import bank.banking_system.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
    List<Notification> findByCustomerIdOrderByCreatedAtDesc(String customerId);
}
