package com.RessourcesProjet.demo.controller;

import com.RessourcesProjet.demo.dto.NotificationDTO;
import com.RessourcesProjet.demo.dto.NotificationRequestDTO;
import com.RessourcesProjet.demo.dto.NotificationUpdateDTO;
import com.RessourcesProjet.demo.enums.notificationStatus;
import com.RessourcesProjet.demo.service.NotificationServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationServiceInterface notificationServiceInterface;

    @Autowired
    public NotificationController(NotificationServiceInterface notificationServiceInterface) {
        this.notificationServiceInterface = notificationServiceInterface;
    }

    @GetMapping
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        return ResponseEntity.ok(notificationServiceInterface.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationServiceInterface.getNotificationById(id));
    }

    @GetMapping("/recipient/{recipientId}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByRecipientId(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationServiceInterface.getNotificationsByRecipientId(recipientId));
    }

    @GetMapping("/recipient/{recipientId}/unread")
    public ResponseEntity<List<NotificationDTO>> getUnreadNotificationsByRecipientId(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationServiceInterface.getUnreadNotificationsByRecipientId(recipientId));
    }

    @GetMapping("/recipient/{recipientId}/count-unread")
    public ResponseEntity<Long> getUnreadNotificationsCount(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationServiceInterface.getUnreadNotificationsCount(recipientId));
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> createNotification(@Valid @RequestBody NotificationRequestDTO requestDTO) {
        NotificationDTO createdNotification = notificationServiceInterface.createNotification(requestDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDTO> updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody NotificationUpdateDTO updateDTO) {
        return ResponseEntity.ok(notificationServiceInterface.updateNotification(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationServiceInterface.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<NotificationDTO> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(notificationServiceInterface.markAsRead(id));
    }

    @PutMapping("/recipient/{recipientId}/read-all")
    public ResponseEntity<List<NotificationDTO>> markAllAsRead(@PathVariable Long recipientId) {
        return ResponseEntity.ok(notificationServiceInterface.markAllAsRead(recipientId));
    }

    @GetMapping("/email-status/{status}")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByEmailStatus(
            @PathVariable notificationStatus status) {
        return ResponseEntity.ok(notificationServiceInterface.getNotificationsByEmailStatus(status));
    }

    @PutMapping("/{id}/email-status/{status}")
    public ResponseEntity<NotificationDTO> updateEmailStatus(
            @PathVariable Long id,
            @PathVariable notificationStatus status) {
        return ResponseEntity.ok(notificationServiceInterface.updateEmailStatus(id, status));
    }
}
