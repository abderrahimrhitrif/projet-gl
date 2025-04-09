package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.ResourceType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "submission_items")
public class SubmissionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private BigDecimal price;
    private ResourceType resourceType;


    private String cpu;  // for computers
    private String ram;  // for computers
    private String hardDisk; // for computers
    private String screen; // for computers

    private Integer printSpeed; // for printers
    private String resolution; // for printers

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private TenderSubmission submission;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private ResourceRequest resourceRequest;


}
