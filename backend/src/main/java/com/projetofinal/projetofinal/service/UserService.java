package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.projetofinal.projetofinal.dtos.UserDto;
import com.projetofinal.projetofinal.model.User;
import com.projetofinal.projetofinal.repository.UserRepository;

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
    public List<UserDto> getAllUserDtoService() {
        List<User> users = repository.findAll();
        return mapUserListToUserDtoListService(users);
    }

    // Método para converter uma lista de objetos users em uma lista de objetos
    // usersDto
    private List<UserDto> mapUserListToUserDtoListService(List<User> users) {
        return users.stream()
                .map(this::mapUserToUserDtoService)
                .collect(Collectors.toList());
    }

    // Converte um Usuário em um UsuarioDto
    private UserDto mapUserToUserDtoService(User user) {
        UserDto dto = new UserDto();
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
    public UserDto getUserIdDtoService(Integer id) {
        User user = repository.findById(id).get();
        UserDto dto = new UserDto(user);
        return dto;
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
