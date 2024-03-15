package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.projetofinal.projetofinal.dtos.MetasFinanceirasDto;
import com.projetofinal.projetofinal.model.MetasFinanceiras;
import com.projetofinal.projetofinal.repository.MetasFinanceirasRepository;

@Service
public class MetasFinanceirasService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private MetasFinanceirasRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as metas financeiras ========================================
    public List<MetasFinanceiras> getAllMetasFinanceiras() {
        return repository.findAll();
    }

    // Trazer todas as metas financeiras Dto ====================================
    public List<MetasFinanceirasDto> getAllMetasFinanceirasDto() {
        List<MetasFinanceiras> metas = repository.findAll();
        return mapMetasFinanceirasListToMetasFinanceirasDtoList(metas);
    }

    // Pega os objetos transformados em DTO e cria uma lista
    public List<MetasFinanceirasDto> mapMetasFinanceirasListToMetasFinanceirasDtoList(List<MetasFinanceiras> metas) {
        return metas.stream()
                .map(this::mapMetasFinanceirasToFinanceirasDto)
                .collect(Collectors.toList());
    }

    // Transforma os objetos model em DTO
    public MetasFinanceirasDto mapMetasFinanceirasToFinanceirasDto(MetasFinanceiras metas) {
        MetasFinanceirasDto dto = new MetasFinanceirasDto();
        // Mapeie os campos para o DTO conforme necessário
        dto.setDescricao(metas.getdescricao());
        dto.setValor(metas.getValor());
        dto.setData(metas.getData());
        return dto;
    }

    // Trazer uma meta financeira pelo id ====================================
    public MetasFinanceiras getMetaFinanceiraId(Integer id) {
        return repository.findById(id).get();
    }

    // Trazer metas financeiras pelo id Dto ==================================
    public MetasFinanceirasDto getMetaFinanceiraDto(Integer id) {
        MetasFinanceiras meta = repository.findById(id).get();
        MetasFinanceirasDto dto = new MetasFinanceirasDto(meta);
        return dto;
    }

    // Criar uma nova meta financeira ==================================
    public ResponseEntity<String> postNovaMetaFinanceira(MetasFinanceiras meta) {
        repository.save(meta);
        return ResponseEntity.ok("Meta financeira criada com sucesso.");
    }

    // Atualizar uma meta financeira por id ==================================
    public ResponseEntity<String> putUpdateMetaFinanceira(Integer id, MetasFinanceiras meta) {
        if (repository.existsById(id)) {
            meta.setId(id);
            repository.save(meta);
            return ResponseEntity.ok("Meta financeira atualizada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Meta Financeira não encontrada.");
        }
    }

    // Deletar uma meta financeira por id ================================
    public ResponseEntity<String> deleteMetaFinanceiraId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Meta financeira deletada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Meta Financeira não encontrada.");
        }
    }
}
