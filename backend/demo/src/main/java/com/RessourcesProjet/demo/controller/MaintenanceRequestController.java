package com.RessourcesProjet.demo.controller;

import com.RessourcesProjet.demo.dto.MaintenanceRequestCreateDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestDTO;
import com.RessourcesProjet.demo.dto.MaintenanceRequestUpdateDTO;
import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import com.RessourcesProjet.demo.service.MaintenanceRequestServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance-requests")
@RequiredArgsConstructor
public class MaintenanceRequestController {

    private final MaintenanceRequestServiceInterface maintenanceRequestServiceInterface;


    @PostMapping
    @PreAuthorize("hasAnyRole('TEACHER', 'DEPARTMENT_HEAD', 'ADMIN')")
    public ResponseEntity<MaintenanceRequestDTO> createMaintenanceRequest(
            @Valid @RequestBody MaintenanceRequestCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(maintenanceRequestServiceInterface.createMaintenanceRequest(dto));
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<MaintenanceRequestDTO> updateMaintenanceRequest(
            @PathVariable Long id,
            @Valid @RequestBody MaintenanceRequestUpdateDTO dto) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.updateMaintenanceRequest(id, dto));
    }


    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<MaintenanceRequestDTO> updateMaintenanceRequestStatus(
            @PathVariable Long id,
            @RequestParam MaintenanceStatus status) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.updateMaintenanceRequestStatus(id, status));
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MaintenanceRequestDTO> getMaintenanceRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getMaintenanceRequestById(id));
    }


    @GetMapping
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<List<MaintenanceRequestDTO>> getAllMaintenanceRequests() {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getAllMaintenanceRequests());
    }


    @GetMapping("/status/{status}")
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<List<MaintenanceRequestDTO>> getMaintenanceRequestsByStatus(
            @PathVariable MaintenanceStatus status) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getMaintenanceRequestsByStatus(status));
    }


    @GetMapping("/requester/{requesterId}")
    @PreAuthorize("hasAnyRole('TEACHER', 'DEPARTMENT_HEAD', 'ADMIN') or #requesterId == authentication.principal.id")
    public ResponseEntity<List<MaintenanceRequestDTO>> getMaintenanceRequestsByRequester(
            @PathVariable Long requesterId) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getMaintenanceRequestsByRequester(requesterId));
    }


    @GetMapping("/resource/{resourceId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MaintenanceRequestDTO>> getMaintenanceRequestsByResource(
            @PathVariable Long resourceId) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getMaintenanceRequestsByResource(resourceId));
    }


    @GetMapping("/date-range")
    @PreAuthorize("hasAnyRole('TECHNICIAN', 'ADMIN')")
    public ResponseEntity<List<MaintenanceRequestDTO>> getMaintenanceRequestsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.getMaintenanceRequestsByDateRange(startDate, endDate));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMaintenanceRequest(@PathVariable Long id) {
        maintenanceRequestServiceInterface.deleteMaintenanceRequest(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/resource/{resourceId}/has-active")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> hasActiveMaintenanceRequests(@PathVariable Long resourceId) {
        return ResponseEntity.ok(maintenanceRequestServiceInterface.hasActiveMaintenanceRequests(resourceId));
    }
}

