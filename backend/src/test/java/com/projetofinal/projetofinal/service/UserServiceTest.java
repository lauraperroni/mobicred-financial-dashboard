package com.projetofinal.projetofinal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

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
            assertTrue(dtoUsers.get(i).getName().equals(users.get(i).getName()));
            assertTrue(dtoUsers.get(i).getEmail().equals(users.get(i).getEmail()));
            assertTrue(dtoUsers.get(i).getId().equals(users.get(i).getId()));
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
        // UserResponseDto result = service.getUserIdDtoService(id);
        // assertNotNull(result);
    }

    @Test
    void testGetUserIdDtoService_UserNotExists() {
        Integer id = 1;
        when(repository.existsById(id)).thenReturn(false);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            service.getUserIdService(id);
        });
        assertEquals("User not found.", exception.getMessage());
    }

    @SuppressWarnings("null")
    @Test
    void testPostNewUserService() {
        // Arrange
        User user = new User();
        user.setName("testUser");
        user.setPassword("testPassword");
        // Act
        ResponseEntity<String> response = service.postNewUserService(user);
        // Assert
        assertEquals("New user created.", response.getBody());
        verify(repository).save(user);
        assertEquals(LocalDate.now(), user.getRegisterDate());
        verify(repository).save(argThat(savedUser -> savedUser.getPassword().startsWith("$2a$")));
    }
}
