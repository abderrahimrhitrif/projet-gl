package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.notificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Long id;
    private String title;
    private String content;
    private String sender;
    private Long recipientId;
    private String recipientUsername;
    private boolean isRead;
    private LocalDateTime createdAt;
    private notificationStatus emailStatus;
}
