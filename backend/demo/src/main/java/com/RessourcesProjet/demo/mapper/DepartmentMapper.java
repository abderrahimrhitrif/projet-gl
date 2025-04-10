package com.RessourcesProjet.demo.mapper;

import com.RessourcesProjet.demo.dto.DepartmentDTO;
import com.RessourcesProjet.demo.dto.DepartmentRequestDTO;
import com.RessourcesProjet.demo.entity.Department;
import com.RessourcesProjet.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DepartmentMapper {

    public DepartmentDTO toDTO(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setDepartmentHeadId(department.getDepartmentHead() != null ?
                department.getDepartmentHead().getId() : null);
        return dto;
    }

    public Department toEntity(DepartmentRequestDTO dto, User departmentHead) {
        if (dto == null) {
            return null;
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setDepartmentHead(departmentHead);

        return department;
    }

    public void updateEntityFromDTO(Department department, DepartmentRequestDTO dto, User departmentHead) {
        if (department == null || dto == null) {
            return;
        }

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setDepartmentHead(departmentHead);
    }

    public List<DepartmentDTO> toDTOList(List<Department> departments) {
        if (departments == null) {
            return null;
        }

        return departments.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
