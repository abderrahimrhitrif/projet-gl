package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.ResourceStatus;
import jakarta.persistence.Inheritance;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "resources")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String inventoryNumber;
    private String brand;
    private LocalDate acquisitionDate;
    private ResourceStatus status;
    private LocalDate warrantyEndDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "assigned_to_user_id")
    private User assignedTo;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "resource")
    private List<MaintenanceRequest> maintenanceRequests = new ArrayList<>();


}
