package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.IssueFrequency;
import com.RessourcesProjet.demo.enums.IssueType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceReportUpdateDTO {
    private LocalDate reportDate;
    private String explanation;
    private LocalDate issueAppearanceDate;
    private IssueFrequency frequency;
    private IssueType issueType;
    private String recommendation;
    private Long technicianId;
}
