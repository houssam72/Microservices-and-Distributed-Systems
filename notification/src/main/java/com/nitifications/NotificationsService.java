package com.nitifications;

import com.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record NotificationsService(NotificationRepository notificationRepository) {

   public void sendNotification(NotificationRequest request) {

        Notification notification= Notification.builder()
                .toCustomerEmail(request.toCustomerEmail())
                .toCustomerId(request.toCustomerId())
                .message(request.message())
                .sentAt(request.sentAt())
                .sender(request.sender())
                .build();

        notificationRepository.save(notification);
    };


}
