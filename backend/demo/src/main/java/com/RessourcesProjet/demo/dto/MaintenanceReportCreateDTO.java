package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.IssueFrequency;
import com.RessourcesProjet.demo.enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceReportCreateDTO {
    @NotNull(message = "La date du rapport est obligatoire")
    private LocalDate reportDate;

    @NotBlank(message = "L'explication est obligatoire")
    private String explanation;

    @NotNull(message = "La date d'apparition du problème est obligatoire")
    private LocalDate issueAppearanceDate;

    @NotNull(message = "La fréquence du problème est obligatoire")
    private IssueFrequency frequency;

    @NotNull(message = "Le type de problème est obligatoire")
    private IssueType issueType;

    @NotBlank(message = "La recommandation est obligatoire")
    private String recommendation;

    @NotNull(message = "L'ID de la demande de maintenance est obligatoire")
    private Long maintenanceRequestId;

    @NotNull(message = "L'ID du technicien est obligatoire")
    private Long technicianId;
}
