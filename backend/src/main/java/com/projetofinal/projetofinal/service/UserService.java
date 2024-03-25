package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.projetofinal.projetofinal.dtos.User.UserResponseDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private UserRepository repository;

    // Métodos que os Endpoints usam ==========================================

    // Trazer todos os usuários ===============================================
    public List<User> getAllUserService() {
        return repository.findAll();
    }

    // Trazer todos os usuários DTO ===========================================
    public List<UserResponseDto> getAllUserDtoService() {
        List<User> users = repository.findAll();
        return mapUserListToUserDtoListService(users);
    }

    // Método para converter uma lista de objetos users em uma lista de objetos
    // usersDto
    private List<UserResponseDto> mapUserListToUserDtoListService(List<User> users) {
        return users.stream()
                .map(this::mapUserToUserDtoService)
                .collect(Collectors.toList());
    }

    // Converte um Usuário em um UsuarioDto
    private UserResponseDto mapUserToUserDtoService(User user) {
        UserResponseDto dto = new UserResponseDto();
        // Mapeie os campos do usuário para o DTO conforme necessário
        dto.setIdDto(user.getId());
        dto.setNameDto(user.getName());
        dto.setEmailDto(user.getEmail());
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz um usuário pelo id ================================================
    @SuppressWarnings("null")
    public User getUserIdService(Integer id) {
        return repository.findById(id).get();
    }

    // Traz um usuário pelo id DTO ============================================
    @SuppressWarnings("null")
    public UserResponseDto getUserIdDtoService(Integer id) {
        if (repository.existsById(id)) {
            User user = repository.findById(id).get();
            UserResponseDto dto = new UserResponseDto(user);
            return dto;
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    // Adicionar novo usuário =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewUserService(User user) {
        repository.save(user);
        return ResponseEntity.ok("New user created.");
    }

    // Update de um usuário por id ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateUserService(Integer id, User user) {
        if (repository.existsById(id)) {
            user.setId(id);
            repository.save(user);
            return ResponseEntity.ok("User updated.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }

    // Deletar um usuário por id =============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteUserIdService(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("User deleted.");
        } else {
            return ResponseEntity.status(404).body("User not found.");
        }
    }
}
