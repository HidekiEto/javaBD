package models;

public class Equipamento {
    private int idEquipamento;
    private String nome;
    private String descricao;
    private int quantidadeDisponivel;
    private boolean ativo;

    public Equipamento() { 
    }

    public Equipamento(String nome, String descricao, int quantidadeDisponivel, boolean ativo) { 
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    public Equipamento(int idEquipamento, String nome, String descricao, int quantidadeDisponivel, boolean ativo) { 
        this.idEquipamento = idEquipamento;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean isAtivo() { 
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString(){
        return "Equipamento [idEquipamento=" + idEquipamento + ", nome=" + nome + ", descricao=" + descricao + ", quantidadeDisponivel=" + quantidadeDisponivel + ", ativo=" + ativo + "]";
    }
}
