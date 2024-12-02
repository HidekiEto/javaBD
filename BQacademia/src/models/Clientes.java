package models;

import java.time.LocalDate;

public class Clientes {
    private int idCliente;
    private String nome, cpf, telefone, email, endereco;
    private LocalDate dataNasc, dataCadastro;
    private boolean ativo;

    public Clientes() { 
    }

    public Clientes(String nome, String cpf,LocalDate dataNasc, String telefone, String email, String endereco, LocalDate dataCadastro, boolean ativo) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.ativo = ativo;
    }

    public Clientes(int idCliente, String nome, String cpf,LocalDate dataNasc, String telefone, String email, String endereco, LocalDate dataCadastro, boolean ativo) {
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

    // Getters and Setters...
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    public LocalDate getDataNasc(){
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc){
        this.dataNasc = dataNasc;
    }
    public String getTelefone(){
        return telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getEndereco(){
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    public LocalDate getDataCadastro(){
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro){
        this.dataCadastro = dataCadastro;
    }
    public boolean isAtivo(){
        return ativo;
    }
    public void setAtivo(boolean ativo){
        this.ativo = ativo;
    }

    @Override
    public String toString(){
        return "Clientes [idCliente=" + idCliente + ", nome=" + nome + ", cpf=" + cpf + ", dataNasc=" + dataNasc + ", telefone=" + telefone + ", email=" + email + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo + "]";
    }

}
