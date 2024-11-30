package repository;

import models.Equipamento;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository{
    
    public void adicionarEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO equipamentos (nome, descricao, quantidade_disponivel, status) VALUES (? ? ? ?)";

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, equipamento.getNome());
                stmt.setString(2, equipamento.getDescricao());
                stmt.setInt(3, equipamento.getQuantidadeDisponivel());
                stmt.setBoolean(4, equipamento.isAtivo());

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Equipamento adicionado com sucesso!");
                }
                
            } catch (SQLException e) {
                System.out.println("Erro ao adicionar equipamento.");
                e.printStackTrace();
            }
    }

    public List<Equipamento> obterTodosEquipamentos(){
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * from equipamentos";

        try (Connection conn = DbConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipamento equipamento = new Equipamento(
                    rs.getInt("idEquipamento"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getInt("quantidadeDisponivel"),
                    rs.getBoolean("ativo")
                );
                equipamentos.add(equipamento);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao obter equipamentos.");
            e.printStackTrace();
        }
        return equipamentos;
    }

    public Equipamento obterEquipamentoPorId(int idEquipamento) {
        String sql = "SELECT * FROM equipamentos WHERE id_equipamento = ?";
        Equipamento equipamento = null;

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idEquipamento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                equipamento = new Equipamento(
                    rs.getInt("idEquipamento"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getInt("quantidadeDisponivel"),
                    rs.getBoolean("ativo")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter equipamento pelo ID");
            e.printStackTrace();
        }
        return equipamento;
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        String sql = 
            "UPDATE equipamentos SET nome = ?, descricao = ?, qtd_disponivel = ?, status = ? WHERE id_equipamento = ?";
        
        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getDescricao());
            stmt.setInt(3, equipamento.getQuantidadeDisponivel());
            stmt.setBoolean(4, equipamento.isAtivo());
            stmt.setInt(5, equipamento.getIdEquipamento());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Equipamento atualizado com sucesso!");
            } else {
                System.out.println("Equipamento mão encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar equipamento");
            e.printStackTrace();
        }
    }
    public void deletarEquipamento(int idEquipamento) {
        String sql = "DELETE FROM equipamentos WHERE id = ?";

        try (Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEquipamento);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Equipamento deletado com sucesso!");
            } else {
                System.out.println("Equipamento não encontrado.");
            }

        } catch (SQLException e){
            System.out.println("Erro ao deletar equipamento");
            e.printStackTrace();
        }
    }
}