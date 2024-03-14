package com.projetofinal.projetofinal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.model.Transacoes;
import com.projetofinal.projetofinal.repository.TransacoesRepository;

@Service
public class TransacoesService {

    @Autowired
    private TransacoesRepository repository;

    // Endpoints
    // =============================================================================

    // Trazer todas as transacoes
    public List<Transacoes> getAllTransacoes() {
        return repository.findAll();
    }

    // Traz uma transacao pelo id
    @SuppressWarnings("null")
    public Transacoes getTransacaoId(Integer id) {
        return repository.findById(id).get();
    }

    // Adicionar nova transacao
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovaTransacao(Transacoes transacao) {
        repository.save(transacao);
        return ResponseEntity.ok("Transação criada com sucesso.");
    }

    // Update de um usuário por id
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

    // Deletar um usuário por id
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
