package com.projetofinal.projetofinal.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetofinal.projetofinal.dtos.User.RegisterDTO;
import com.projetofinal.projetofinal.dtos.User.UserResponseDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUserService() {
        return repository.findAll();
    }

    public List<UserResponseDto> getAllUserDtoService() {
        List<User> users = repository.findAll();
        return mapUserListToUserDtoListService(users);
    }

    private List<UserResponseDto> mapUserListToUserDtoListService(List<User> users) {
        return users.stream()
                .map(this::mapUserToUserDtoService)
                .collect(Collectors.toList());
    }

    private UserResponseDto mapUserToUserDtoService(User user) {
        UserResponseDto dto = new UserResponseDto(user);
        return dto;
    }

    public User getUserIdService(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
    }

    public UserResponseDto getUserIdDtoService(Integer id) {
        User user = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found."));
        return new UserResponseDto(user);
    }

    public ResponseEntity<String> postNewUserService(User user) {
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRegisterDate(LocalDate.now());
        repository.save(user);
        return ResponseEntity.ok("New user created.");
    }

    public ResponseEntity<String> putUpdateUserService(Integer id, User user) {
        if (repository.existsById(id)) {
            user.setId(id);
            repository.save(user);
            return ResponseEntity.ok("User updated.");
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    public ResponseEntity<String> deleteUserIdService(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("User deleted.");
        } else {
            throw new EntityNotFoundException("User not found.");
        }
    }

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO) {
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        user.setPassword(encryptedPassword);
        user.setRegisterDate(LocalDate.now());

        // Adicione os campos adicionais aqui
        user.setCpf(registerDTO.getCpf());
        user.setName(registerDTO.getName());
        user.setNumber(registerDTO.getNumber());
        user.setZipCode(registerDTO.getZipCode());
        user.setStreet(registerDTO.getStreet());
        user.setDistrict(registerDTO.getDistrict());
        user.setState(registerDTO.getState());
        user.setCity(registerDTO.getCity());
        user.setComplement(registerDTO.getComplement());

        repository.save(user);
        return ResponseEntity.ok("New user registered.");
    }
}
