package com.RessourcesProjet.demo.service;

import com.RessourcesProjet.demo.dto.MaintenanceRequestCreateDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestUpdateDTO;
import com.RessourcesProjet.demo.entity.MaintenanceRequest;
import com.RessourcesProjet.demo.entity.Resource;
import com.RessourcesProjet.demo.entity.User;
import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import com.RessourcesProjet.demo.mapper.MaintenanceRequestMapper;
import com.RessourcesProjet.demo.repository.MaintenanceRequestRepository;
import com.RessourcesProjet.demo.repository.ResourceRepository;
import com.RessourcesProjet.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceRequestService implements MaintenanceRequestServiceInterface {

    private final MaintenanceRequestRepository maintenanceRequestRepository;
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;
    private final MaintenanceRequestMapper maintenanceRequestMapper;


    @Override
    @Transactional
    public MaintenanceRequestDTO createMaintenanceRequest(MaintenanceRequestCreateDTO dto) {
        User requester = userRepository.findById(dto.getRequesterId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getRequesterId()));

        Resource resource = resourceRepository.findById(dto.getResourceId())
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + dto.getResourceId()));

        MaintenanceRequest maintenanceRequest = maintenanceRequestMapper.toEntity(dto, requester, resource);
        MaintenanceRequest savedRequest = maintenanceRequestRepository.save(maintenanceRequest);

        return maintenanceRequestMapper.toDTO(savedRequest);
    }

    @Override
    @Transactional
    public MaintenanceRequestDTO updateMaintenanceRequest(Long id, MaintenanceRequestUpdateDTO dto) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found with id: " + id));


        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            maintenanceRequest.setDescription(dto.getDescription());
        }


        if (dto.getStatus() != null) {
            maintenanceRequest.setStatus(dto.getStatus());
        }

        MaintenanceRequest updatedRequest = maintenanceRequestRepository.save(maintenanceRequest);

        return maintenanceRequestMapper.toDTO(updatedRequest);
    }


    @Override
    @Transactional
    public MaintenanceRequestDTO updateMaintenanceRequestStatus(Long id, MaintenanceStatus status) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found with id: " + id));

        maintenanceRequest.setStatus(status);
        MaintenanceRequest updatedRequest = maintenanceRequestRepository.save(maintenanceRequest);

        return maintenanceRequestMapper.toDTO(updatedRequest);
    }


    @Override
    public MaintenanceRequestDTO getMaintenanceRequestById(Long id) {
        MaintenanceRequest maintenanceRequest = maintenanceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance request not found with id: " + id));

        return maintenanceRequestMapper.toDTO(maintenanceRequest);
    }


    @Override
    public List<MaintenanceRequestDTO> getAllMaintenanceRequests() {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findAll();
        return maintenanceRequestMapper.toDTOList(maintenanceRequests);
    }


    @Override
    public List<MaintenanceRequestDTO> getMaintenanceRequestsByStatus(MaintenanceStatus status) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findByStatus(status);
        return maintenanceRequestMapper.toDTOList(maintenanceRequests);
    }


    @Override
    public List<MaintenanceRequestDTO> getMaintenanceRequestsByRequester(Long requesterId) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findByRequesterId(requesterId);
        return maintenanceRequestMapper.toDTOList(maintenanceRequests);
    }


    @Override
    public List<MaintenanceRequestDTO> getMaintenanceRequestsByResource(Long resourceId) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findByResourceId(resourceId);
        return maintenanceRequestMapper.toDTOList(maintenanceRequests);
    }


    @Override
    public List<MaintenanceRequestDTO> getMaintenanceRequestsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<MaintenanceRequest> maintenanceRequests = maintenanceRequestRepository.findByRequestDateBetween(startDate, endDate);
        return maintenanceRequestMapper.toDTOList(maintenanceRequests);
    }


    @Override
    @Transactional
    public void deleteMaintenanceRequest(Long id) {
        if (!maintenanceRequestRepository.existsById(id)) {
            throw new RuntimeException("Maintenance request not found with id: " + id);
        }

        maintenanceRequestRepository.deleteById(id);
    }


    @Override
    public boolean hasActiveMaintenanceRequests(Long resourceId) {
        return maintenanceRequestRepository. hasActiveMaintenanceRequests(resourceId, MaintenanceStatus.COMPLETED);
    }
}
