package com.RessourcesProjet.demo.dto;
import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequestCreateDTO {
    @NotNull(message = "La date de requête est obligatoire")
    private LocalDate requestDate;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotNull(message = "L'identifiant du demandeur est obligatoire")
    private Long requesterId;

    @NotNull(message = "L'identifiant de la ressource est obligatoire")
    private Long resourceId;

    private MaintenanceStatus status = MaintenanceStatus.PENDING;
}
