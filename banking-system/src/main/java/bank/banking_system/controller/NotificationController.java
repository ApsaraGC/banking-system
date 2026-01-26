package bank.banking_system.controller;

import bank.banking_system.notification.Notification;
import bank.banking_system.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getNotifications(Authentication authentication) {
        String username = authentication.getName();
        return notificationRepository.findByCustomerIdOrderByCreatedAtDesc(username);
    }

    @PutMapping("/read/{id}")
    public String markAsRead(@PathVariable String id){
        Notification notification = notificationRepository
                .findById(id)
                .orElseThrow();
        notification.setRead(true);
        notificationRepository.save(notification);

        return "Notification marked as read";
    }

}
