package models;
// define que esta classe pertence ao pacote "models"

public class Equipamento {
  

    private int idEquipamento; 
    private String nome; 
    private String descricao; 
    private int quantidadeDisponivel;
    private boolean ativo; // indica se o equipamento está ativo (disponível para uso)

    public Equipamento() {
        // construtor vazio (Útil para criar um equipamento sem inicializar os atributos no momento da criação)
    }

    public Equipamento(String nome, String descricao, int quantidadeDisponivel, boolean ativo) { 
        // construtor para criar um equipamento sem ID, geralmente usado antes de ser salvo no banco de dados
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    public Equipamento(int idEquipamento, String nome, String descricao, int quantidadeDisponivel, boolean ativo) { 
        // construtor que inclui o ID, usado quando os dados já existem no banco de dados
        this.idEquipamento = idEquipamento;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.ativo = ativo;
    }

    // métodos getters e setters permitem acessar e modificar os atributos do equipamento

    public int getIdEquipamento() {
        return idEquipamento; // retorna o ID do equipamento
    }

    public String getNome() {
        return nome; // retorna o nome do equipamento
    }

    public void setNome(String nome) {
        this.nome = nome; // define o nome do equipamento
    }

    public String getDescricao() {
        return descricao; // retorna a descrição do equipamento
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao; // define a descrição do equipamento
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel; // retorna a quantidade disponível do equipamento
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel; // define a quantidade disponível do equipamento
    }

    public boolean isAtivo() { 
        return ativo; // verifica se o equipamento está ativo
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo; // define o status de atividade do equipamento
    }

    @Override
    public String toString(){
        // método que retorna uma representação textual do equipamento, útil para depuração ou logs
        return "Equipamento [idEquipamento=" + idEquipamento + ", nome=" + nome + 
               ", descricao=" + descricao + ", quantidadeDisponivel=" + quantidadeDisponivel + 
               ", ativo=" + ativo + "]";
    }
}
