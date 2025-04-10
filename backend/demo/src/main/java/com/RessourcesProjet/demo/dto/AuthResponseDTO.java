package com.RessourcesProjet.demo.dto;

import com.RessourcesProjet.demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDTO {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private String token;
    private Long departmentId;
}
