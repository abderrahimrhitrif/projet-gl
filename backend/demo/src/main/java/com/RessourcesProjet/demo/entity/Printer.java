package com.RessourcesProjet.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "printers")
public class Printer extends Resource {
    private Integer printSpeed;
    private String resolution;


}