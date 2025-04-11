package com.RessourcesProjet.demo.service;

import com.RessourcesProjet.demo.dto.NotificationDTO;
import com.RessourcesProjet.demo.dto.NotificationRequestDTO;
import com.RessourcesProjet.demo.dto.NotificationUpdateDTO;
import com.RessourcesProjet.demo.entity.Notification;
import com.RessourcesProjet.demo.entity.User;
import com.RessourcesProjet.demo.enums.notificationStatus;
import com.RessourcesProjet.demo.mapper.NotificationMapper;
import com.RessourcesProjet.demo.repository.NotificationRepository;
import com.RessourcesProjet.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService implements NotificationServiceInterface {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final NotificationMapper notificationMapper;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository,
                               UserRepository userRepository,
                               NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getAllNotifications() {
        return notificationMapper.toDTOList(notificationRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public NotificationDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));
        return notificationMapper.toDTO(notification);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getNotificationsByRecipientId(Long recipientId) {
        return notificationMapper.toDTOList(notificationRepository.findByRecipientIdOrderByCreatedAtDesc(recipientId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getUnreadNotificationsByRecipientId(Long recipientId) {
        return notificationMapper.toDTOList(notificationRepository.findByRecipientIdAndIsRead(recipientId, false));
    }

    @Override
    @Transactional(readOnly = true)
    public Long getUnreadNotificationsCount(Long recipientId) {
        return notificationRepository.countUnreadNotificationsByRecipientId(recipientId);
    }

    @Override
    @Transactional
    public NotificationDTO createNotification(NotificationRequestDTO requestDTO) {
        User recipient = userRepository.findById(requestDTO.getRecipientId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + requestDTO.getRecipientId()));

        Notification notification = notificationMapper.toEntity(requestDTO, recipient);
        Notification savedNotification = notificationRepository.save(notification);

        return notificationMapper.toDTO(savedNotification);
    }

    @Override
    @Transactional
    public NotificationDTO updateNotification(Long id, NotificationUpdateDTO updateDTO) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));

        notificationMapper.updateEntityFromDTO(notification, updateDTO);
        Notification updatedNotification = notificationRepository.save(notification);

        return notificationMapper.toDTO(updatedNotification);
    }

    @Override
    @Transactional
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new EntityNotFoundException("Notification not found with id: " + id);
        }
        notificationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public NotificationDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));

        notification.setRead(true);
        Notification updatedNotification = notificationRepository.save(notification);

        return notificationMapper.toDTO(updatedNotification);
    }

    @Override
    @Transactional
    public List<NotificationDTO> markAllAsRead(Long recipientId) {
        List<Notification> unreadNotifications = notificationRepository.findByRecipientIdAndIsRead(recipientId, false);

        for (Notification notification : unreadNotifications) {
            notification.setRead(true);
        }

        List<Notification> updatedNotifications = notificationRepository.saveAll(unreadNotifications);
        return notificationMapper.toDTOList(updatedNotifications);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getNotificationsByEmailStatus(notificationStatus status) {
        return notificationMapper.toDTOList(notificationRepository.findByEmailStatus(status));
    }

    @Override
    @Transactional
    public NotificationDTO updateEmailStatus(Long id, notificationStatus status) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: " + id));

        notification.setEmailStatus(status);
        Notification updatedNotification = notificationRepository.save(notification);

        return notificationMapper.toDTO(updatedNotification);
    }
}
