package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.notificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "Le contenu est obligatoire")
    @Size(max = 1000, message = "Le contenu ne peut pas dépasser 1000 caractères")
    private String content;

    @NotBlank(message = "L'expéditeur est obligatoire")
    private String sender;

    @NotNull(message = "Le destinataire est obligatoire")
    private Long recipientId;

    private notificationStatus emailStatus;
}
