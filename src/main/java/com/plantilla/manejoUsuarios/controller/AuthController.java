package com.plantilla.manejoUsuarios.controller;

import com.plantilla.manejoUsuarios.DTO.LoginRequest;
import com.plantilla.manejoUsuarios.DTO.LoginResponse;
import com.plantilla.manejoUsuarios.DTO.TokenPayloadDTO;
import com.plantilla.manejoUsuarios.service.TokenService;
import com.plantilla.manejoUsuarios.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    public final TokenService tokenService;
    public final UserService userService;

    public AuthController(TokenService tokenService, UserService userService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    //metodo para login
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        // Primero validamos las credenciales comparando el usuario y la contraseña con el hash almacenado en la base de datos
        TokenPayloadDTO payload = userService.validarYArmarPayload(loginRequest);
        // Si las credenciales son válidas, armamos el payload del token y lo generamos
        String token = tokenService.generarToken(payload);
        // Retornamos el token en la respuesta
        return new LoginResponse(token);
    }
}
