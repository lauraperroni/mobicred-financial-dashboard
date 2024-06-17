package com.projetofinal.projetofinal.controller;

import org.springframework.web.bind.annotation.RestController;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountResponseDto;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountPutDto;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountRequestDto;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.service.BankAccountService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/bankaccounts")
public class ControllerBankAccount {

    // Cria a dependencia do service pra conversar com banco de dados
    @Autowired
    private BankAccountService service;

    // Endpoints ===============================================================

    // Trazer todas as accounts bancarias DTO ====================================
    @GetMapping("/all")
    public List<BankAccountResponseDto> getAllBankAccountDto() {
        return service.getAllBankAccountDtoService();
    }

    // Trazer todas as accounts bancarias DTO ====================================
    @GetMapping("/user/all")
    public List<BankAccountResponseDto> getAllBankAccountDtoByUser(@AuthenticationPrincipal User user) {
        return service.getAllBankAccountDtoServicebyUser(user);
    }

    // Traz uma account pelo id DTO ==============================================
    @GetMapping("/{id}")
    public ResponseEntity<?> getBankAccountId(@PathVariable Integer id) {
        BankAccountResponseDto dto = service.getBankAccountDtoIdService(id);
        if (dto != null) {
            return ResponseEntity.ok().body(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Adicionar nova account bancaria ===========================================
    @PostMapping("/new")
    public ResponseEntity<String> postNewBankAccount(@AuthenticationPrincipal User user,
            @RequestBody BankAccountRequestDto account) {
        try {

            String email = user.getUsername();
            service.postNewBankAccountDtoService(email, account);
            return ResponseEntity.ok("Nova conta bancária registrada.");
        } catch (Exception e) {
            e.printStackTrace(); // Para depuração
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro ao criar nova conta bancária.");
        }
    }

    // Update de um usuário por id =============================================
    // @PutMapping("/update/{id}")
    // public ResponseEntity<String> putUpdateBankAccountId(@PathVariable Integer id,
    //         @RequestBody BankAccount account) {
    //     account.setId(id);
    //     return service.putUpdateBankAccountIdService(id, account);
    // }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> putUpdateBankAccountId(@PathVariable Integer id,
            @RequestBody BankAccountPutDto account) {
        return service.putUpdateBankAccountIdService(id, account);
    }

    // Deletar um usuário por id ===============================================
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBankAccountId(@PathVariable Integer id) {
        return service.deleteBankAccountIdService(id);
    }
}
