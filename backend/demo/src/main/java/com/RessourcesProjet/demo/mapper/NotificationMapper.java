package com.RessourcesProjet.demo.mapper;

import com.RessourcesProjet.demo.dto.NotificationDTO;
import com.RessourcesProjet.demo.dto.NotificationRequestDTO;
import com.RessourcesProjet.demo.dto.NotificationUpdateDTO;
import com.RessourcesProjet.demo.entity.Notification;
import com.RessourcesProjet.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NotificationMapper {

    public NotificationDTO toDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        NotificationDTO dto = new NotificationDTO();
        dto.setId(notification.getId());
        dto.setTitle(notification.getTitle());
        dto.setContent(notification.getContent());
        dto.setSender(notification.getSender());

        if (notification.getRecipient() != null) {
            dto.setRecipientId(notification.getRecipient().getId());
            dto.setRecipientUsername(notification.getRecipient().getUsername());
        }

        dto.setRead(notification.isRead());
        dto.setCreatedAt(notification.getCreatedAt());
        dto.setEmailStatus(notification.getEmailStatus());

        return dto;
    }

    public Notification toEntity(NotificationRequestDTO dto, User recipient) {
        if (dto == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setTitle(dto.getTitle());
        notification.setContent(dto.getContent());
        notification.setSender(dto.getSender());
        notification.setRecipient(recipient);
        notification.setEmailStatus(dto.getEmailStatus());

        return notification;
    }

    public void updateEntityFromDTO(Notification notification, NotificationUpdateDTO dto) {
        if (notification == null || dto == null) {
            return;
        }

        if (dto.getIsRead() != null) {
            notification.setRead(dto.getIsRead());
        }

        if (dto.getEmailStatus() != null) {
            notification.setEmailStatus(dto.getEmailStatus());
        }
    }

    public List<NotificationDTO> toDTOList(List<Notification> notifications) {
        if (notifications == null) {
            return null;
        }

        return notifications.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
