package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.RequestStatus;
import com.RessourcesProjet.demo.enums.ResourceType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "resource_requests")
public class ResourceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ResourceType resourceType;
    private String specifications;
    private String justification;
    private RequestStatus status;


    private String cpu;  // for computers
    private String ram;  // for computers
    private String hardDisk; // for computers
    private String screen; // for computers

    private Integer printSpeed; // for printers
    private String resolution; // for printers

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;


}
