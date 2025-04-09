package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.SubmissionStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tender_submissions")
public class TenderSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate deliveryDate;
    private Integer warrantyDurationMonths;
    private BigDecimal totalPrice;
    private SubmissionStatus status;
    private String rejectionReason;

    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "submission")
    private List<SubmissionItem> items = new ArrayList<>();


}
