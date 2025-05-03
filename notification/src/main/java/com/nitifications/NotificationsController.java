package com.nitifications;

import com.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/notifications")
public record NotificationsController(NotificationsService notificationsService) {

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest) {
         log.info("New notification... {}", notificationRequest);
         this.notificationsService.sendNotification(notificationRequest);

    };

}
