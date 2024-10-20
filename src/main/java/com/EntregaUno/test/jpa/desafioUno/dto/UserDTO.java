package com.EntregaUno.test.jpa.desafioUno.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO que representa un usuario")
public class UserDTO {

    @Schema(description = "ID único del usuario", example = "1")
    private Long id;

    @Schema(description = "Nombre del usuario", example = "Juan Pérez")
    private String name;

    @Schema(description = "Correo electrónico del usuario", example = "juan.perez@mail.com")
    private String email;

    public UserDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
