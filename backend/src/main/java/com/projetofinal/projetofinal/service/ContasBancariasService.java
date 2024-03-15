package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.ContasBancariasDto;
import com.projetofinal.projetofinal.model.ContasBancarias;
import com.projetofinal.projetofinal.repository.ContasBancariasRepository;

@Service
public class ContasBancariasService {

    // Cria a dependencia do repository pra conversar com banco de dados
    @Autowired
    private ContasBancariasRepository repository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as contas bancarias =========================================
    public List<ContasBancarias> getAllContasBancarias() {
        return repository.findAll();
    }

    // Trazer todas as contas Dto ===========================================

    public List<ContasBancariasDto> getAllContasBancariasDto() {
        List<ContasBancarias> contas = repository.findAll();
        return mapContasBancariasListToContasBancariasDtoList(contas);
    }

    // Método para converter uma lista de objetos ContasBancarias em uma lista de
    // objetos ContasBancariasDto
    private List<ContasBancariasDto> mapContasBancariasListToContasBancariasDtoList(List<ContasBancarias> contas) {
        return contas.stream()
                .map(this::mapContaBancariaToContaBancariaDto) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma ContaBancaria em uma ContaBancariaDto
    private ContasBancariasDto mapContaBancariaToContaBancariaDto(ContasBancarias conta) {
        ContasBancariasDto dto = new ContasBancariasDto();
        // Mapeie os campos do usuário para o DTO conforme necessário
        dto.setId(conta.getId());
        dto.setSaldo(conta.getSaldo());
        dto.setTipoConta(conta.getTipoConta());
        // Faça o mesmo para outros campos
        return dto;
    }

    // Traz uma conta pelo id ===============================================
    @SuppressWarnings("null")
    public ContasBancarias getContaBancariaId(Integer id) {
        return repository.findById(id).get();
    }

    // Traz uma conta pelo id DTO ===========================================
    @SuppressWarnings("null")
    public ContasBancariasDto getContaBancariaIdDto(Integer id) {
        ContasBancarias conta = repository.findById(id).get(); // instancia um objeto pelo Id
        ContasBancariasDto dto = new ContasBancariasDto(conta); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova conta =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNovaContaBancaria(ContasBancarias conta) {
        repository.save(conta);
        return ResponseEntity.ok("Conta bancária criada com sucesso.");
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateContaBancaria(Integer id, ContasBancarias conta) {
        if (repository.existsById(id)) {
            conta.setId(id);
            repository.save(conta);
            return ResponseEntity.ok("Conta bancária atualizada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Conta bancária não encontrada.");
        }
    }

    // Deletar uma conta bancaria por id ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteContaBancariaId(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Conta Bancária deletada com sucesso.");
        } else {
            return ResponseEntity.status(404).body("Conta Bancária não encontrada.");
        }
    }
}
