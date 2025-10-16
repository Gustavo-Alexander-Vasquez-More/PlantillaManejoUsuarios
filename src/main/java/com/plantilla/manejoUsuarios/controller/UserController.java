package com.plantilla.manejoUsuarios.controller;

import com.plantilla.manejoUsuarios.DTO.UserDTO;
import com.plantilla.manejoUsuarios.model.User;
import com.plantilla.manejoUsuarios.repository.UserRepository;
import com.plantilla.manejoUsuarios.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
 public final UserService userService;
 public final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    //endopint para crear un nuevo usuario
    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody User body){
        try {
            User created = userService.createUser(body);
            UserDTO dto = new UserDTO(created.getId(), created.getUsername(), created.getRole());
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //endpoint para devolver todos los usuarios
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> dtos = users.stream()
                .map(user -> new UserDTO(user.getId(), user.getUsername(), user.getRole()))
                .toList();
        return ResponseEntity.ok(dtos);
    }

    //endpoint para devolver un usuario por id
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getRole()));
    }

    //endopoint para devolver un usuario por username
    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        if(user == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.ok(new UserDTO(user.getId(), user.getUsername(), user.getRole()));
    }

    //endpoint para eliminar un usuario por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    //endpoint para actualizar un usuario por id
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User body){
        User user = userRepository.findById(id).orElse(null);
        if(user == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User updated = userService.updateUser(id, body);
        UserDTO dto = new UserDTO(updated.getId(), updated.getUsername(), updated.getRole());
        return ResponseEntity.ok(dto);
    }
}
