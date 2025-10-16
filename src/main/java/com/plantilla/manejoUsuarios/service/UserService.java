/*
En esta clase se implementara la logica de negocio relacionada con los usuarios
como la creacion, actualizacion, eliminacion y consulta de usuarios
junto con la gestion de roles y permisos
Tambien se manejara el hashing de contraseñas y la validacion de credenciales
*/

package com.plantilla.manejoUsuarios.service;
import com.plantilla.manejoUsuarios.DTO.LoginRequest;
import com.plantilla.manejoUsuarios.DTO.TokenPayloadDTO;
import com.plantilla.manejoUsuarios.model.User;
import com.plantilla.manejoUsuarios.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //metodo para validar y armar el payload del token
    public TokenPayloadDTO validarYArmarPayload(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            throw new BadCredentialsException("Credenciales inválidas");
        }
        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("Credenciales inválidas");
        }
        //Si las credenciales son validas, armamos el payload del token y lo generamos
        TokenPayloadDTO payload= new TokenPayloadDTO(user.getId(), user.getUsername(), user.getRole());
        return payload;
    }

    //metodo para crear un nuevo usuario
    public User createUser(User body){
        if(body == null){
            throw new IllegalArgumentException("El cuerpo de la solicitud no puede ser nulo");
        }
        //hasheamos la contraseña antes de guardarla en la base de datos
        body.setPassword(passwordEncoder.encode(body.getPassword()));
        return userRepository.save(body);
    }

    //metodo para actualizar un usuario existente
    public User updateUser(Long id, User body) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        //actualizamos los campos del usuario
        user.setUsername(body.getUsername());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        user.setRole(body.getRole());
        return userRepository.save(user);
    }

}
