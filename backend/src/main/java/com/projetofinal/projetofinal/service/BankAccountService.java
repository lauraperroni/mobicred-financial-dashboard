package com.projetofinal.projetofinal.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountResponseDto;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountPutDto;
import com.projetofinal.projetofinal.dtos.BankAccount.BankAccountRequestDto;
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

    public List<BankAccountResponseDto> getAllBankAccountDtoService() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return mapBankAccountListToBankAccountDtoListService(accounts);
    }

    // Método para converter uma lista de objetos BankAccount em uma lista de
    private List<BankAccountResponseDto> mapBankAccountListToBankAccountDtoListService(List<BankAccount> accounts) {
        return accounts.stream()
                .map(this::mapBankAccountToBankAccountDtoService) // usando o método abaixo
                .collect(Collectors.toList());
    }

    // Converte uma accountBancaria em uma accountBancariaDto
    private BankAccountResponseDto mapBankAccountToBankAccountDtoService(BankAccount account) {
        BankAccountResponseDto dto = new BankAccountResponseDto(account);
        return dto;
    }

    // Traz uma account pelo id ===============================================
    @SuppressWarnings("null")
    public BankAccount getBankAccountIdService(Integer id) {
        return bankAccountRepository.findById(id).get();
    }

    // Traz uma account pelo id DTO ===========================================
    @SuppressWarnings("null")
    public BankAccountResponseDto getBankAccountDtoIdService(Integer id) {
        BankAccount account = bankAccountRepository.findById(id).get(); // instancia um objeto pelo Id
        BankAccountResponseDto dto = new BankAccountResponseDto(account); // transforma em um objeto dto
        return dto;
    }

    // Adicionar nova account =================================================
    @SuppressWarnings("null")
    public void postNewBankAccountService(BankAccount account) {
        bankAccountRepository.save(account);
    }

    // Adicionar nova account DTO =============================================
    @SuppressWarnings({ "rawtypes"})
    public ResponseEntity postNewBankAccountDtoService(String userName, BankAccountRequestDto account) {
        BankAccount acc = new BankAccount(account);
        
        User user = (User) userRepository.findByEmail(userName);
        acc.setUser(user);
        user.addBankAccountToList(acc);
        bankAccountRepository.save(acc);
        return ResponseEntity.ok("Nova conta criada.");
    }

    // Update de uma conta por id ==============================================
    @SuppressWarnings("null")
    public ResponseEntity<String> putUpdateBankAccountIdService(Integer id, BankAccountPutDto account) {
        if (bankAccountRepository.existsById(id)) {
            BankAccount acc = bankAccountRepository.findById(id).get();
            acc.putData(account);
            
            bankAccountRepository.save(acc);
            return ResponseEntity.ok("Updated bank account.");
        } else {
            throw new EntityNotFoundException("Bank account not found.");
        }
    }

    // Deletar uma account bancaria por id ========================================
    @SuppressWarnings("null")
    public ResponseEntity<String> deleteBankAccountIdService(Integer id) {
        if (bankAccountRepository.existsById(id)) {
            bankAccountRepository.deleteById(id);
            return ResponseEntity.ok("Bank account deleted.");
        } else {
            throw new EntityNotFoundException("Bank account not found.");
        }
    }

    // Trazer todas as accounts Dto de um user ===========================================

    public List<BankAccountResponseDto> getAllBankAccountDtoServicebyUser(User user) {
        User user2 = (User) userRepository.findByEmail(user.getUsername());
        List<BankAccount> accounts = bankAccountRepository.findByUserId(user2.getId());
        return mapBankAccountListToBankAccountDtoListService(accounts);
    }

}
