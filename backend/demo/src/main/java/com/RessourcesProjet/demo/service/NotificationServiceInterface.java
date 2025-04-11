package com.RessourcesProjet.demo.service;

import com.RessourcesProjet.demo.dto.NotificationDTO;
import com.RessourcesProjet.demo.dto.NotificationRequestDTO;
import com.RessourcesProjet.demo.dto.NotificationUpdateDTO;
import com.RessourcesProjet.demo.enums.notificationStatus;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationServiceInterface {
    @Transactional(readOnly = true)
    List<NotificationDTO> getAllNotifications();

    @Transactional(readOnly = true)
    NotificationDTO getNotificationById(Long id);

    @Transactional(readOnly = true)
    List<NotificationDTO> getNotificationsByRecipientId(Long recipientId);

    @Transactional(readOnly = true)
    List<NotificationDTO> getUnreadNotificationsByRecipientId(Long recipientId);

    @Transactional(readOnly = true)
    Long getUnreadNotificationsCount(Long recipientId);

    @Transactional
    NotificationDTO createNotification(NotificationRequestDTO requestDTO);

    @Transactional
    NotificationDTO updateNotification(Long id, NotificationUpdateDTO updateDTO);

    @Transactional
    void deleteNotification(Long id);

    @Transactional
    NotificationDTO markAsRead(Long id);

    @Transactional
    List<NotificationDTO> markAllAsRead(Long recipientId);

    @Transactional(readOnly = true)
    List<NotificationDTO> getNotificationsByEmailStatus(notificationStatus status);

    @Transactional
    NotificationDTO updateEmailStatus(Long id, notificationStatus status);
}
