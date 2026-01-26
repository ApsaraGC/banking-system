package bank.banking_system.event;

import bank.banking_system.notification.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TransactionNotificationEvent {
    private final String customerId;
    private final NotificationType type;
    private final String message;
}
