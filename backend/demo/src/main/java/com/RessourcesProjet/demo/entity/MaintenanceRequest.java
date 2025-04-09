package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "maintenance_requests")
public class MaintenanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate requestDate;
    private String description;
    private MaintenanceStatus status;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @OneToOne(mappedBy = "maintenanceRequest")
    private MaintenanceReport report;


}
