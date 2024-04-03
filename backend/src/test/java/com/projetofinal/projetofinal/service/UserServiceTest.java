package com.projetofinal.projetofinal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.projetofinal.projetofinal.dtos.User.UserResponseDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    // Testando o buscar todos os usuários
    @Test
    void testGetAllUserDtoService() {
        User user1 = new User(1, "123", "laura", "laura@gmail.com", "Abc123.!", "rua", 1, "bairro", "abc", "poa", "rs",
                "90000-000");
        List<User> users = List.of(user1);

        when(repository.findAll()).thenReturn(users);

        var dtoUsers = service.getAllUserDtoService();

        for (int i = 0; i < users.size(); i++) {
            assertTrue(dtoUsers.get(i).getUserName().equals(users.get(i).getName()));
            assertTrue(dtoUsers.get(i).getUserEmail().equals(users.get(i).getEmail()));
            assertTrue(dtoUsers.get(i).getUserId().equals(users.get(i).getId()));
        }
    }

    // Testando se o usuário por id existe
    @Test
    void testGetUserIdDtoService_UserExists() {
        // Dado
        Integer id = 1;
        User user = new User();
        when(repository.existsById(id)).thenReturn(true);
        when(repository.findById(id)).thenReturn(Optional.of(user));
        UserResponseDto result = service.getUserIdDtoService(id);
        assertNotNull(result);
    }

    @Test
    void testGetUserIdDtoService_UserNotExists() {
        Integer id = 1;
        when(repository.existsById(id)).thenReturn(false);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getUserIdDtoService(id);
        });
        assertEquals("User not found.", exception.getMessage());
    }

}