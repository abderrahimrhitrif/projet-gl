package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRequestUpdateDTO {

    private String description;
    private MaintenanceStatus status;

}
