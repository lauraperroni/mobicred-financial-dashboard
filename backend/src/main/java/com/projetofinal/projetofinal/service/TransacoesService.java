package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.TransacoesDto;
import com.projetofinal.projetofinal.model.Transacoes;
import com.projetofinal.projetofinal.repository.TransacoesRepository;

@Service
public class TransacoesService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private TransacoesRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as transacoes ===============================================
    public List<Transacoes> getAllTransacoes() {
        return repository.findAll();
    }

    // Trazer todas as transacoes Dto ===========================================

    public List<TransacoesDto> getAllTransacoesDto() {
        List<Transacoes> transacoes = repository.findAll();
        return mapTransacoesListToTransacoesDtoList(transacoes);
    }

    // Método para converter uma lista de objetos Transacoes em uma lista de objetos
    // TransacoesDto
    private List<TransacoesDto> mapTransacoesListToTransacoesDtoList(List<Transacoes> transacoes) {
        return transacoes.stream()
                .map(this::mapTransacaoToTransacaoDto) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma Transacao em uma TransacaoDto
    private TransacoesDto mapTransacaoToTransacaoDto(Transacoes transacao) {
        TransacoesDto dto = new TransacoesDto();
        // Mapeie os campos do usuário para o DTO conforme necessário
        dto.setId(transacao.getId());
        dto.setValor(transacao.getValor());
        dto.setidcategoria(transacao.getidcategoria());
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz uma transacao pelo id ===============================================
    @SuppressWarnings("null")
    public Transacoes getTransacaoId(Integer id) {
        return repository.findById(id).get();
    }

    // Traz uma transacao pelo id DTO ===========================================
    @SuppressWarnings("null")
    public TransacoesDto getTransacaoIdDto(Integer id) {
        Transacoes transacao = repository.findById(id).get(); // instancia um objeto pelo Id
        TransacoesDto dto = new TransacoesDto(transacao); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova transacao =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovaTransacao(Transacoes transacao) {
        repository.save(transacao);
        return ResponseEntity.ok("Transação criada com sucesso.");
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateTransacao(Integer id, Transacoes transacao) {
        if (repository.existsById(id)) {
            transacao.setId(id);
            repository.save(transacao);
            return ResponseEntity.ok("Transação atualizada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Transação não encontrada.");
        }
    }

    // Deletar um usuário por id ================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteTransacaoId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Transação deletada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Transação não encontrada.");
        }
    }
}
