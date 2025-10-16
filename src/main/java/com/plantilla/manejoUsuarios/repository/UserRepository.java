package com.plantilla.manejoUsuarios.repository;

import com.plantilla.manejoUsuarios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //Metodos personalizados
    User findByUsername(String username);
}
