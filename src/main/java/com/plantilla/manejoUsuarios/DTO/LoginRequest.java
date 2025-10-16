/*
Este DTO se utiliza para capturar los datos de inicio de sesión enviados por el cliente,
Se captura el nombre de usuario y la contraseña.
Se utiliza en el controlador de autenticación para procesar las solicitudes de login.
*/

package com.plantilla.manejoUsuarios.DTO;

public class LoginRequest {
    private String username;
    private String password;

    //Constructor
    public LoginRequest() {}

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Getters & Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
