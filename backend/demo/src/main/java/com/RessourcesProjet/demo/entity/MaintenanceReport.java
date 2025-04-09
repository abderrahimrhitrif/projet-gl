package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.IssueFrequency;
import com.RessourcesProjet.demo.enums.IssueType;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "maintenance_reports")
public class MaintenanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate reportDate;
    private String explanation;
    private LocalDate issueAppearanceDate;
    private IssueFrequency frequency; // RARE, FREQUENT, PERMANENT
    private IssueType issueType; // SOFTWARE, HARDWARE
    private String recommendation; // repair or replace

    @OneToOne
    @JoinColumn(name = "maintenance_request_id")
    private MaintenanceRequest maintenanceRequest;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private User technician;

    @PrePersist
    @PreUpdate
    public void validateOrdreImprimante() {
        Resource materiel = maintenanceRequest.getResource();

        if(materiel instanceof Printer) {
            if(this.issueType != issueType.HARDWARE) {
                throw new IllegalArgumentException("Pour une imprimante, le constat doit être d'ordre MATERIEL.");
            }
        }
    }

}
