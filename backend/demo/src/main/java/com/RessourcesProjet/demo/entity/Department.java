package com.RessourcesProjet.demo.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne
    @JoinColumn(name = "head_user_id")
    private User departmentHead;

    @OneToMany(mappedBy = "department")
    private List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<Resource> resources = new ArrayList<>();

    @OneToMany(mappedBy = "department")
    private List<ResourceRequest> requests = new ArrayList<>();


}