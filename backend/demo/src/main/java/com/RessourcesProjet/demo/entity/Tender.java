package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.TenderStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tenders")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TenderStatus status;

    @OneToMany(mappedBy = "tender")
    private List<TenderSubmission> submissions = new ArrayList<>();

    @OneToMany(mappedBy = "tender")
    private List<ResourceRequest> requests = new ArrayList<>();


    @AssertTrue(message = "La date de début doit être antérieure à la date de fin")
    public boolean isValidPeriode() {
        if(startDate == null || endDate == null) {
            return true;
        }
        return startDate.isBefore(endDate);
    }
}
