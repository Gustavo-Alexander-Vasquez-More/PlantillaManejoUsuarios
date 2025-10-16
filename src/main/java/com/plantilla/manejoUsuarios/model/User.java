package com.plantilla.manejoUsuarios.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users") //Definimos el nombre de la tabla en la base de datos
public class User {
    //Attributos
    @Id //Definimos la llave primaria ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Definimos la estrate|gia de generacion de la llave primaria auto incremental
    private Long id;
    @Column(unique = true, nullable = false) //username unico y no nulo
    private String username;
    @Column(nullable = false) //password no nulo
    private String password;
    @Column(nullable = false) //role no nulo
    @Enumerated(EnumType.STRING) //Almacenar el enum como String en la base de datos
    private Role role;

    //CONSTRUCTOR
    public User() {} //Constructor vacio necesario para JPA
    //No incluimos el ID porque lo gestiona la base de datos
    public User(String username, String password, Role role) {
        this.username=username;
        this.password=password;
        this.role=role;
    }

    //Getters & Setters
    public Long getId() {
        return id;
    }
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
