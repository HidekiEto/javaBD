package models;
// define que esta classe pertence ao pacote "models"

import java.time.LocalDate;
// importa a classe LocalDate, usada para trabalhar com datas sem informações de horário

public class Clientes {
    // define a classe Clientes, que representa um cliente no sistema

    private int idCliente; // identificador único do cliente
    private String nome, cpf, telefone, email, endereco; // atributos básicos de um cliente
    private LocalDate dataNasc, dataCadastro; // atributos para armazenar a data de nascimento e de cadastro do cliente
    private boolean ativo; // indica se o cliente está ativo no sistema

    public Clientes() { 
        // construtor vazio (Útil para criar um cliente sem inicializar os atributos de imediato)
    }

    public Clientes(String nome, String cpf, LocalDate dataNasc, String telefone, String email, String endereco, LocalDate dataCadastro, boolean ativo) {
        // construtor para criar um cliente sem um ID, útil antes do cliente ser salvo no banco de dados
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public Clientes(int idCliente, String nome, String cpf, LocalDate dataNasc, String telefone, String email, String endereco, LocalDate dataCadastro, boolean ativo) {
        // construtor para criar um cliente já com ID, geralmente usado quando os dados vêm do banco de dados
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    // métodos getters e setters permitem acessar e modificar os atributos do cliente de forma controlada
  
    public int getIdCliente() {
        return idCliente; // retorna o ID do cliente
    }
    
    public String getNome() {
        return nome; // retorna o nome do cliente
    }
    public void setNome(String nome) {
        this.nome = nome; // define o nome do cliente
    }
    
    public String getCpf() {
        return cpf; // retorna o CPF do cliente
    }
    public void setCpf(String cpf) {
        this.cpf = cpf; // define o CPF do cliente
    }
    
    public LocalDate getDataNasc() {
        return dataNasc; // retorna a data de nascimento do cliente
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc; // define a data de nascimento do cliente
    }
    
    public String getTelefone() {
        return telefone; // retorna o telefone do cliente
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone; // define o telefone do cliente
    }
    
    public String getEmail() {
        return email; // retorna o email do cliente
    }
    public void setEmail(String email) {
        this.email = email; // define o email do cliente
    }
    
    public String getEndereco() {
        return endereco; // retorna o endereço do cliente
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco; // define o endereço do cliente
    }
    
    public LocalDate getDataCadastro() {
        return dataCadastro; // retorna a data de cadastro do cliente
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro; // define a data de cadastro do cliente
    }
    
    public boolean isAtivo() {
        return ativo; // verifica se o cliente está ativo
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo; // define o status de atividade do cliente
    }

    @Override
    public String toString() {
        // método para retornar uma representação textual do cliente, útil para debug e logs
        return "Clientes [idCliente=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + 
               ", dataNasc=" + dataNasc + ", telefone=" + telefone + ", email=" + email + 
               ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + "]";
    }
}
