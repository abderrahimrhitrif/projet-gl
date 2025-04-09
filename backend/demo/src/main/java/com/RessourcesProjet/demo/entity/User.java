package com.RessourcesProjet.demo.entity;

import com.RessourcesProjet.demo.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "user")
    private List<ResourceRequest> requests = new ArrayList<>();

    @OneToMany(mappedBy = "assignedTo")
    private List<Resource> assignedResources = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;


    @OneToOne(mappedBy = "user")
    private Supplier supplier;


}
