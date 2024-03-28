package com.projetofinal.projetofinal.model.User;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.projetofinal.projetofinal.model.BankAccount.BankAccount;
import com.projetofinal.projetofinal.model.FinancialGoal.FinancialGoal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Users")
public class User extends RepresentationModel<User> implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private UserRole role;

    @CPF
    @NotBlank(message = "CPF is mandatory")
    private String cpf;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter and one special character")
    private String password;

    @NotBlank(message = "Rua is mandatory")
    private String rua;

    @NotBlank(message = "Número is mandatory")
    private Integer numero;

    @NotBlank(message = "Bairro is mandatory")
    private String bairro;

    private String complemento;

    @NotBlank(message = "Cidade is mandatory")
    private String cidade;

    @NotBlank(message = "Estado is mandatory")
    @Size(min = 2, max = 2, message = "State abbreviation must have 2 characters")
    private String estado;

    @NotBlank(message = "CEP is mandatory")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid ZIP code format. Should be XXXXX-XXX")
    private String cep;

    private LocalDate dataRegistro;
    // Listas de relacionamento entre tabelas

    // User - BankAcconut
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    // User - FinancialGoal
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<FinancialGoal> financialGoals;

    // Construtores ============================================================

    // Construtor sem argumentos
    public User() {
    }

    // Construtor all args
    public User(Integer id, String cpf, String name, String email, String password, String rua, Integer numero,
            String bairro, String complemento, String cidade, String estado, String cep, LocalDate dataRegistro) {
        this.id = id;
        this.cpf = cpf;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.dataRegistro = LocalDate.now();
    }

    public User(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters e Setters =======================================================
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    // Método de relação entre tabelas ================================

    // User - BankAccount 1-*
    public void addBankAccountToList(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
        bankAccount.setUser(this);
    }

    // User - FinancialGoal 1-*
    public void addFinancialGoalToList(FinancialGoal financialGoal) {
        financialGoals.add(financialGoal);
        financialGoal.setUser(this);
    }

    // UserDetails Methods ============================================

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
