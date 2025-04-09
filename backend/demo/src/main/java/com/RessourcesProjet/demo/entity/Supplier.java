package com.RessourcesProjet.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;
    private String location;
    private String address;
    private String website;
    private String managerName;
    private boolean blacklisted;
    private String blacklistReason;
    // Date à laquelle le fournisseur a été placé en liste noire
    private LocalDate dateBlacklisted;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "supplier")
    private List<TenderSubmission> submissions = new ArrayList<>();

    @OneToMany(mappedBy = "supplier")
    private List<Resource> suppliedResources = new ArrayList<>();


}
