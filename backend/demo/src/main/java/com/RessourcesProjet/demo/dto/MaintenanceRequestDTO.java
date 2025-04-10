package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequestDTO {
    private Long id;
    private LocalDate requestDate;
    private String description;
    private MaintenanceStatus status;
    private Long requesterId;
    private String requesterName;
    private Long resourceId;
    private String resourceName;
    private boolean hasReport;
}