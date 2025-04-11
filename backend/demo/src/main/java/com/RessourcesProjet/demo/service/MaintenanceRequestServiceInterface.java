package com.RessourcesProjet.demo.service;

import com.RessourcesProjet.demo.dto.MaintenanceRequestCreateDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestUpdateDTO;
import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceRequestServiceInterface {
    @Transactional
    MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestCreateDTO dto);

    @Transactional
    MaintenanceRequestDTO updateMaintenanceRequest(Long id, MaintenanceRequestUpdateDTO dto);

    @Transactional
    MaintenanceRequestDTO updateMaintenanceRequestStatus(Long id, MaintenanceStatus status);

    MaintenanceRequestDTO getMaintenanceRequestById(Long id);

    List<MaintenanceRequestDTO> getAllMaintenanceRequests();

    List<MaintenanceRequestDTO> getMaintenanceRequestsByStatus(MaintenanceStatus status);

    List<MaintenanceRequestDTO> getMaintenanceRequestsByRequester(Long requesterId);

    List<MaintenanceRequestDTO> getMaintenanceRequestsByResource(Long resourceId);

    List<MaintenanceRequestDTO> getMaintenanceRequestsByDateRange(LocalDate startDate, LocalDate endDate);

    @Transactional
    void deleteMaintenanceRequest(Long id);

    boolean hasActiveMaintenanceRequests(Long resourceId);
}
