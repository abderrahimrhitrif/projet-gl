package com.RessourcesProjet.demo.repository;

import com.RessourcesProjet.demo.entity.MaintenanceRequest;
import com.RessourcesProjet.demo.enums.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRequestRepository extends JpaRepository<MaintenanceRequest, Long> {


    List<MaintenanceRequest> findByStatus(MaintenanceStatus status);


    List<MaintenanceRequest> findByRequesterId(Long requesterId);


    List<MaintenanceRequest> findByResourceId(Long resourceId);


    List<MaintenanceRequest> findByRequestDateBetween(LocalDate startDate, LocalDate endDate);


    List<MaintenanceRequest> findByRequesterIdAndStatus(Long requesterId, MaintenanceStatus status);


    List<MaintenanceRequest> findByResourceIdAndStatus(Long resourceId, MaintenanceStatus status);

    @Query("SELECT CASE WHEN COUNT(m) > 0 THEN true ELSE false END FROM MaintenanceRequest m WHERE m.resource.id = :resourceId AND m.status != :status")
    boolean hasActiveMaintenanceRequests(@Param("resourceId") Long resourceId, @Param("status") MaintenanceStatus status);
}
