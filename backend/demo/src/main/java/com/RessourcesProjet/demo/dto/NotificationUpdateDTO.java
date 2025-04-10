package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.notificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationUpdateDTO {
    private Boolean isRead;
    private notificationStatus emailStatus;
}
