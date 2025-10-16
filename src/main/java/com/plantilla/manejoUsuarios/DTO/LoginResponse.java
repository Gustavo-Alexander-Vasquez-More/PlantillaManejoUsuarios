/*Este DTO se utiliza para enviar la respuesta de inicio de sesión al cliente,
especificamente token JWT generado.
*/

package com.plantilla.manejoUsuarios.DTO;

public class LoginResponse {
    private String token;

    //Constructor
    public LoginResponse() {}
    public LoginResponse(String token) {
        this.token = token;
    }

    //Getters & Setters

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

