package com.RessourcesProjet.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "computers")
public class Computer extends Resource {
    private String cpu;
    private String ram;
    private String hardDisk;
    private String screen;


}
