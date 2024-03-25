package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountDto;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountRequestDto;
import com.projetofinal.projetofinal.exception.RestExceptionHandler;
import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.User.User;
import com.projetofinal.projetofinal.repository.BankAccount.BankAccountRepository;
import com.projetofinal.projetofinal.repository.User.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BankAccountService {

    // Cria a dependencia do bankAccountRepository pra conversar com banco de dados
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private UserRepository userRepository;

    // Métodos que os Endpoints usam ============================================

    // Trazer todas as accounts bancarias =========================================
    public List<BankAccount> getAllBankAccountService() {
        return bankAccountRepository.findAll();
    }

    // Trazer todas as accounts Dto ===========================================

    public List<BankAccountDto> getAllBankAccountDtoService() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return mapBankAccountListToBankAccountDtoListService(accounts);
    }

    // Método para converter uma lista de objetos BankAccount em uma lista de
    private List<BankAccountDto> mapBankAccountListToBankAccountDtoListService(List<BankAccount> accounts) {
        return accounts.stream()
                .map(this::mapBankAccountToBankAccountDtoService) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma accountBancaria em uma accountBancariaDto
    private BankAccountDto mapBankAccountToBankAccountDtoService(BankAccount account) {
        BankAccountDto dto = new BankAccountDto(account);
        return dto;
    }

    // Traz uma account pelo id ===============================================
    @SuppressWarnings("null")
    public BankAccount getBankAccountIdService(Integer id) {
        return bankAccountRepository.findById(id).get();
    }

    // Traz uma account pelo id DTO ===========================================
    @SuppressWarnings("null")
    public BankAccountDto getBankAccountDtoIdService(Integer id) {
        BankAccount account = bankAccountRepository.findById(id).get(); // instancia um objeto pelo Id
        BankAccountDto dto = new BankAccountDto(account); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova account =================================================
    @SuppressWarnings("null")
    public ResponseEntity<String> postNewBankAccountService(BankAccount account) {
        bankAccountRepository.save(account);
        return ResponseEntity.ok("New account created.");
    }

    // Adicionar nova account DTO ============================================
    @SuppressWarnings({ "null", "rawtypes" })
    public ResponseEntity postNewBankAccountDtoService(BankAccountRequestDto account) {
        try {
            User user = userRepository.findById(account.userId()).get();
            BankAccount acc = new BankAccount(account.accountType(), account.balance(), account.name());
            user.addBankAccountToList(acc);
            bankAccountRepository.save(acc);
            return ResponseEntity.ok("New account created.");
        } catch (EntityNotFoundException ex) {
            return RestExceptionHandler.HandlingErrorEntityNotFound(ex);
        }
    }

    // Update de um usuário por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateBankAccountIdService(Integer id, BankAccount account) {
        if (bankAccountRepository.existsById(id)) {
            account.setId(id);
            bankAccountRepository.save(account);
            return ResponseEntity.ok("Updated bank account.");
        } else {
            return ResponseEntity.status(404).body("Bank account not found.");
        }
    }

    // Deletar uma account bancaria por id ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteBankAccountIdService(Integer id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
            return ResponseEntity.ok("Bank account deleted.");
        } else {
            return ResponseEntity.status(404).body("Bank account not found.");
        }
    }
}
