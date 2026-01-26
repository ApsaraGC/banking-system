package bank.banking_system.event;

import bank.banking_system.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final NotificationService notificationService;

    @EventListener
    public void handleTransactionEvent(TransactionNotificationEvent event) {
        notificationService.send(
                event.getCustomerId(),
                event.getType(),
                event.getMessage()
        );

    }
}
