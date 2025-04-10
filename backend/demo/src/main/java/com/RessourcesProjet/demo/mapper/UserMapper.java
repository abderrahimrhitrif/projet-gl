package com.RessourcesProjet.demo.mapper;

import com.RessourcesProjet.demo.dto.AuthResponseDTO;
import com.RessourcesProjet.demo.dto.UserDTO;
import com.RessourcesProjet.demo.dto.UserRequestDTO;
import com.RessourcesProjet.demo.dto.UserResponseDTO;

import com.RessourcesProjet.demo.entity.Department;
import com.RessourcesProjet.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .hasSupplier(user.getSupplier() != null)
                .build();
    }

    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return UserResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .departmentName(user.getDepartment() != null ? user.getDepartment().getName() : null)
                .isSupplier(user.getSupplier() != null)
                .build();
    }

    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        if (user == null) {
            return null;
        }

        return AuthResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .token(token)
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .build();
    }

    public User toEntity(UserRequestDTO dto, Department department) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); //  il faudrait encoder le mot de passe
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setDepartment(department);

        return user;
    }

    public void updateEntityFromDTO(User user, UserRequestDTO dto, Department department) {
        if (user == null || dto == null) {
            return;
        }

        user.setUsername(dto.getUsername());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            user.setPassword(dto.getPassword()); // n oublier pas l encoder avant update
        }
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        user.setDepartment(department);
    }
}
