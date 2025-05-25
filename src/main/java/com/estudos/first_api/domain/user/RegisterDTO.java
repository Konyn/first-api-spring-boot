package com.estudos.first_api.domain.user;

public record RegisterDTO(
                String password,
                String name,
                String email,
                UserRole role) {

}
