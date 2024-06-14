package com.projetofinal.projetofinal.dtos.User;

public record UserPutPasswordDto(
        String email,
        String newPassword) {
}
