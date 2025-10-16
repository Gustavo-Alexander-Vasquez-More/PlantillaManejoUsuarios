/*
Este DTO se utiliza para transferir datos de usuario entre capas,
permitiendo exponer solo la información necesaria (por ejemplo, id, username, role)
y evitando incluir datos sensibles como la contraseña.
Útil para respuestas de API o consultas de usuario sin exponer la entidad completa.
*/

package com.plantilla.manejoUsuarios.DTO;

import com.plantilla.manejoUsuarios.model.Role;

public class UserDTO {
    private Long id;
    private String username;
    private Role role;

    //Constructor
    public UserDTO() {}
    public UserDTO(Long id, String username, Role role) {
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
