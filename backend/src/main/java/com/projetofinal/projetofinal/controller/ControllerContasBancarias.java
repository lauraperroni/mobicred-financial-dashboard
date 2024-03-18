package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.projetofinal.dtos.ContasBancariasDto;
import com.projetofinal.projetofinal.model.ContasBancarias;
import com.projetofinal.projetofinal.service.ContasBancariasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/contasbancarias")
public class ControllerContasBancarias {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private ContasBancariasService service;

    // Endpoints ===============================================================

    // Trazer todas as contas bancarias DTO ====================================
    @GetMapping("/todas")
    public List<ContasBancariasDto> getAllContasBancariasDto() {
        return service.getAllContasBancariasDto();
    }

    // Traz uma conta pelo id DTO ==============================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getContaBancariaId(@PathVariable Integer id) {
        ContasBancariasDto dto = service.getContaBancariaIdDto(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar nova conta bancaria ===========================================
    @PostMapping("/nova")
    public ResponseEntity<String> postNovaContaBancaria(@RequestBody ContasBancarias conta) {
        service.postNovaContaBancaria(conta);
        return ResponseEntity.ok("Conta Bancaria registrada com sucesso!");
    }

    // Update de um usuário por id =============================================
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> putUpdateContaBancaria(@PathVariable Integer id, @RequestBody ContasBancarias conta) {
        conta.setId(id);
        return service.putUpdateContaBancaria(id, conta);
    }

    // Deletar um usuário por id ===============================================
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deleteContaBancariaId(@PathVariable Integer id) {
        return service.deleteContaBancariaId(id);
    }
}
