package com.RessourcesProjet.demo.mapper;

import com.RessourcesProjet.demo.dto.MaintenanceReportCreateDTO;
import com.RessourcesProjet.demo.dto.MaintenanceReportDTO;

import com.RessourcesProjet.demo.dto.MaintenanceReportUpdateDTO;
import com.RessourcesProjet.demo.entity.MaintenanceReport;
import com.RessourcesProjet.demo.entity.MaintenanceRequest;
import com.RessourcesProjet.demo.entity.User;
import com.RessourcesProjet.demo.entity.Resource;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaintenanceReportMapper {

    public MaintenanceReportDTO toDTO(MaintenanceReport report) {
        if (report == null) {
            return null;
        }

        MaintenanceReportDTO dto = new MaintenanceReportDTO();
        dto.setId(report.getId());
        dto.setReportDate(report.getReportDate());
        dto.setExplanation(report.getExplanation());
        dto.setIssueAppearanceDate(report.getIssueAppearanceDate());
        dto.setFrequency(report.getFrequency());
        dto.setIssueType(report.getIssueType());
        dto.setRecommendation(report.getRecommendation());

        if (report.getMaintenanceRequest() != null) {
            dto.setMaintenanceRequestId(report.getMaintenanceRequest().getId());
            dto.setMaintenanceRequestDescription(report.getMaintenanceRequest().getDescription());

            if (report.getMaintenanceRequest().getResource() != null) {
                dto.setResourceId(report.getMaintenanceRequest().getResource().getId());
                dto.setResourceName(report.getMaintenanceRequest().getResource().getName());
            }
        }

        if (report.getTechnician() != null) {
            dto.setTechnicianId(report.getTechnician().getId());
            dto.setTechnicianName(report.getTechnician().getUsername());
        }

        return dto;
    }

    public MaintenanceReport toEntity(MaintenanceReportCreateDTO dto, MaintenanceRequest maintenanceRequest, User technician) {
        if (dto == null) {
            return null;
        }

        MaintenanceReport report = new MaintenanceReport();
        report.setReportDate(dto.getReportDate());
        report.setExplanation(dto.getExplanation());
        report.setIssueAppearanceDate(dto.getIssueAppearanceDate());
        report.setFrequency(dto.getFrequency());
        report.setIssueType(dto.getIssueType());
        report.setRecommendation(dto.getRecommendation());
        report.setMaintenanceRequest(maintenanceRequest);
        report.setTechnician(technician);

        return report;
    }

    public void updateEntityFromDTO(MaintenanceReport report, MaintenanceReportUpdateDTO dto, User technician) {
        if (report == null || dto == null) {
            return;
        }

        if (dto.getReportDate() != null) {
            report.setReportDate(dto.getReportDate());
        }

        if (dto.getExplanation() != null && !dto.getExplanation().isEmpty()) {
            report.setExplanation(dto.getExplanation());
        }

        if (dto.getIssueAppearanceDate() != null) {
            report.setIssueAppearanceDate(dto.getIssueAppearanceDate());
        }

        if (dto.getFrequency() != null) {
            report.setFrequency(dto.getFrequency());
        }

        if (dto.getIssueType() != null) {
            report.setIssueType(dto.getIssueType());
        }

        if (dto.getRecommendation() != null && !dto.getRecommendation().isEmpty()) {
            report.setRecommendation(dto.getRecommendation());
        }

        if (dto.getTechnicianId() != null && technician != null) {
            report.setTechnician(technician);
        }
    }

    public List<MaintenanceReportDTO> toDTOList(List<MaintenanceReport> reports) {
        if (reports == null) {
            return null;
        }

        return reports.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
