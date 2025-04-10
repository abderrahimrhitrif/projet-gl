package com.RessourcesProjet.demo.mapper;

import com.RessourcesProjet.demo.dto.MaintenanceRequestCreateDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestUpdateDTO;
import com.RessourcesProjet.demo.entity.MaintenanceRequest;
import com.RessourcesProjet.demo.entity.Resource;
import com.RessourcesProjet.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaintenanceRequestMapper {

    public MaintenanceRequestDTO toDTO(MaintenanceRequest maintenanceRequest) {
        if (maintenanceRequest == null) {
            return null;
        }

        MaintenanceRequestDTO dto = new MaintenanceRequestDTO();
        dto.setId(maintenanceRequest.getId());
        dto.setRequestDate(maintenanceRequest.getRequestDate());
        dto.setDescription(maintenanceRequest.getDescription());
        dto.setStatus(maintenanceRequest.getStatus());

        if (maintenanceRequest.getRequester() != null) {
            dto.setRequesterId(maintenanceRequest.getRequester().getId());
            dto.setRequesterName(maintenanceRequest.getRequester().getUsername());
        }

        if (maintenanceRequest.getResource() != null) {
            dto.setResourceId(maintenanceRequest.getResource().getId());
            dto.setResourceName(maintenanceRequest.getResource().getName());
        }

        dto.setHasReport(maintenanceRequest.getReport() != null);

        return dto;
    }

    public MaintenanceRequest toEntity(MaintenanceRequestCreateDTO dto, User requester, Resource resource) {
        if (dto == null) {
            return null;
        }

        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setRequestDate(dto.getRequestDate());
        maintenanceRequest.setDescription(dto.getDescription());
        maintenanceRequest.setStatus(dto.getStatus());
        maintenanceRequest.setRequester(requester);
        maintenanceRequest.setResource(resource);

        return maintenanceRequest;
    }

    public void updateEntityFromDTO(MaintenanceRequest maintenanceRequest, MaintenanceRequestUpdateDTO dto) {
        if (maintenanceRequest == null || dto == null) {
            return;
        }



        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            maintenanceRequest.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null) {
            maintenanceRequest.setStatus(dto.getStatus());
        }


    }

    public List<MaintenanceRequestDTO> toDTOList(List<MaintenanceRequest> maintenanceRequests) {
        if (maintenanceRequests == null) {
            return null;
        }

        return maintenanceRequests.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
