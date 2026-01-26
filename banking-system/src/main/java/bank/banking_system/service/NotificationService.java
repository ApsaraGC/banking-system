package bank.banking_system.service;

import bank.banking_system.notification.Notification;
import bank.banking_system.notification.NotificationType;
import bank.banking_system.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void  send(String customerId,
                                  NotificationType type,
                                  String message){

        Notification notification = Notification.builder()
                .customerId(customerId)
                .type(type)
                .message(message)
                .read(false)
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
