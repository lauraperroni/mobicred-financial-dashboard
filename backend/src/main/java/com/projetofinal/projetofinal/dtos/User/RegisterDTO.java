package com.projetofinal.projetofinal.dtos.User;

import com.projetofinal.projetofinal.model.User.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {

}