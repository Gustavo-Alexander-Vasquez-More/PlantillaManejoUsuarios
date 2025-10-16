/*
Este DTO se utiliza para construir el payload del token JWT,
permitiendo transferir solo la información necesaria del usuario (id, username, role).
No se inncluyen datos sensibles como la contraseña.
*/

package com.plantilla.manejoUsuarios.DTO;

import com.plantilla.manejoUsuarios.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class TokenPayloadDTO {
    private Long id;
    private String username;
    private Role role;

    //Constructor
    public TokenPayloadDTO() {}
    public TokenPayloadDTO(Long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    //Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
