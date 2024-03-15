package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.projetofinal.projetofinal.dtos.UsuariosDto;
import com.projetofinal.projetofinal.model.Usuarios;
import com.projetofinal.projetofinal.repository.UsuariosRepository;

@Service
public class UsuariosService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private UsuariosRepository repository;

    // Métodos que os Endpoints usam ==========================================

    // Trazer todos os usuários ===============================================
    public List<Usuarios> getAllUsuarios() {
        return repository.findAll();
    }

    // Trazer todos os usuários DTO ===========================================
    public List<UsuariosDto> getAllUsuariosDto() {
        List<Usuarios> usuarios = repository.findAll();
        return mapUsuariosListToUsuariosDtoList(usuarios);
    }

    // Método para converter uma lista de objetos Usuarios em uma lista de objetos
    // UsuariosDto
    private List<UsuariosDto> mapUsuariosListToUsuariosDtoList(List<Usuarios> usuarios) {
        return usuarios.stream()
                .map(this::mapUsuarioToUsuarioDto)
                .collect(Collectors.toList());
    }

    // Converte um Usuário em um UsuarioDto
    private UsuariosDto mapUsuarioToUsuarioDto(Usuarios usuario) {
        UsuariosDto dto = new UsuariosDto();
        // Mapeie os campos do usuário para o DTO conforme necessário
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz um usuário pelo id ================================================
    @SuppressWarnings("null")
    public Usuarios getUsuarioId(Integer id) {
        return repository.findById(id).get();
    }

    // Traz um usuário pelo id DTO ============================================
    @SuppressWarnings("null")
    public UsuariosDto getUsuarioIdDto(Integer id) {
        Usuarios user = repository.findById(id).get();
        UsuariosDto dto = new UsuariosDto(user);
        return dto;
    }

    // Adicionar novo usuário =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovoUsuario(Usuarios usuario) {
        repository.save(usuario);
        return ResponseEntity.ok("Usuário criado com sucesso.");
    }

    // Update de um usuário por id ============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateUsuario(Integer id, Usuarios usuario) {
        if (repository.existsById(id)) {
            usuario.setId(id);
            repository.save(usuario);
            return ResponseEntity.ok("Usuário atualizado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
    }

    // Deletar um usuário por id =============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteUsuarioId(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado.");
        }
    }
}
