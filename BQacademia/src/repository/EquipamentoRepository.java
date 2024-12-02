package repository;

// importa as classes necessárias para trabalhar com equipamentos e conexão ao banco de dados
import models.Equipamento;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoRepository {

    // método para adicionar um equipamento ao banco de dados
    public void adicionarEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO equipamentos (nome, descricao, qtd_disponivel, ativo) VALUES (?, ?, ?, ?)";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            // define os valores dos parâmetros na query
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getDescricao());
            stmt.setInt(3, equipamento.getQuantidadeDisponivel());
            stmt.setBoolean(4, equipamento.isAtivo());

            // executa a query e verifica se algum registro foi inserido
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Equipamento adicionado com sucesso!");
            }
        } catch (SQLException e) {
            // trata possíveis erros na inserção do equipamento
            System.out.println("Erro ao adicionar equipamento");
            e.printStackTrace();
        }
    }

    // método para obter todos os equipamentos do banco de dados
    public List<Equipamento> obterTodosEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM equipamentos";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
             Statement stmt = conn.createStatement(); // cria um statement para executar a query
             ResultSet rs = stmt.executeQuery(sql)) { // executa a consulta e armazena o resultado

            // repete os resultados da consulta
            while (rs.next()) {
                Equipamento equipamento = new Equipamento(
                        rs.getInt("id_equipamento"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("qtd_disponivel"),
                        rs.getBoolean("ativo")
                );
                equipamentos.add(equipamento); // adiciona o equipamento à lista
            }
        } catch (SQLException e) {
            // trata possíveis erros na consulta
            System.out.println("Erro ao obter equipamentos");
            e.printStackTrace();
        }
        return equipamentos;
    }

    // método para obter um equipamento pelo ID
    public Equipamento obterEquipamentoPorId(int idEquipamento) {
        String sql = "SELECT * FROM equipamentos WHERE id_equipamento = ?";
        Equipamento equipamento = null;

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            stmt.setInt(1, idEquipamento); // define o ID do equipamento como parâmetro
            ResultSet rs = stmt.executeQuery(); // executa a consulta

            if (rs.next()) {
                equipamento = new Equipamento(
                        rs.getInt("id_equipamento"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("qtd_disponivel"),
                        rs.getBoolean("ativo")
                );
            }
        } catch (SQLException e) {
            // trata possíveis erros na consulta
            System.out.println("Erro ao obter equipamento pelo ID");
            e.printStackTrace();
        }
        return equipamento;
    }

    // método para atualizar as informações de um equipamento
    public void atualizarEquipamento(Equipamento equipamento) {
        String sql = "UPDATE equipamentos SET nome = ?, descricao = ?, qtd_disponivel = ?, ativo = ? WHERE id_equipamento = ?";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            // define os valores dos parâmetros na query
            stmt.setString(1, equipamento.getNome());
            stmt.setString(2, equipamento.getDescricao());
            stmt.setInt(3, equipamento.getQuantidadeDisponivel());
            stmt.setBoolean(4, equipamento.isAtivo());
            stmt.setInt(5, equipamento.getIdEquipamento());

            // executa a query e verifica se algum registro foi atualizado
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Equipamento atualizado com sucesso!");
            } else {
                System.out.println("Equipamento não encontrado");
            }
        } catch (SQLException e) {
            // trata possíveis erros na atualização do equipamento
            System.out.println("Erro ao atualizar equipamento");
            e.printStackTrace();
        }
    }

    // método para deletar um equipamento do banco de dados
    public void deletarEquipamento(int idEquipamento) {
        String sql = "DELETE FROM equipamentos WHERE id_equipamento = ?";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            stmt.setInt(1, idEquipamento); // define o ID do equipamento como parâmetro

            // executa a query e verifica se algum registro foi deletado
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Equipamento deletado com sucesso!");
            } else {
                System.out.println("Equipamento não encontrado");
            }
        } catch (SQLException e) {
            // trata possíveis erros na remoção do equipamento
            System.out.println("Erro ao deletar equipamento");
            e.printStackTrace();
        }
    }
}
