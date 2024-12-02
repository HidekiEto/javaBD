package repository;

// importa as classes necessárias para trabalhar com clientes e conexão ao banco de dados
import models.Clientes;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepository {

    // método para adicionar um cliente ao banco de dados
    public void adicionarCliente(Clientes cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, data_nasc, telefone, email, endereco, data_cadastro, ativo) VALUES (?,?,?,?,?,?,?,?)";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            // define os valores dos parâmetros na query
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNasc()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getEndereco());
            stmt.setDate(7, java.sql.Date.valueOf(cliente.getDataCadastro()));
            stmt.setBoolean(8, cliente.isAtivo());

            // executa a query e verifica se algum registro foi inserido
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente adicionado com sucesso!");
            }
        } catch (SQLException e) {
            // trata possíveis erros na inserção do cliente
            System.out.println("Erro ao adicionar cliente");
            e.printStackTrace();
        }
    }

    // método para obter todos os clientes do banco de dados
    public List<Clientes> obterTodosClientes() {
        List<Clientes> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
                Statement stmt = conn.createStatement(); // cria um statement para executar a query
                ResultSet rs = stmt.executeQuery(sql)) { // executa a consulta e armazena o resultado

            // repete os resultados da consulta
            while (rs.next()) {
                Clientes cliente = new Clientes(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nasc").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        rs.getBoolean("ativo"));
                clientes.add(cliente); // adiciona o cliente à lista
            }
        } catch (SQLException e) {
            // trata possíveis erros na consulta
            System.out.println("Erro ao obter clientes");
            e.printStackTrace();
        }
        return clientes;
    }

    // método para obter um cliente pelo ID
    public Clientes obterClientePorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Clientes cliente = null;

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            stmt.setInt(1, idCliente); // define o ID do cliente como parâmetro
            ResultSet rs = stmt.executeQuery(); // executa a consulta

            if (rs.next()) {
                cliente = new Clientes(
                        rs.getInt("id_cliente"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("data_nasc").toLocalDate(),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco"),
                        rs.getDate("data_cadastro").toLocalDate(),
                        rs.getBoolean("ativo"));
            }
        } catch (SQLException e) {
            // trata possíveis erros na consulta
            System.out.println("Erro ao obter cliente pelo ID");
            e.printStackTrace();
        }
        return cliente;
    }

    // método para atualizar as informações de um cliente
    public void atualizarCliente(Clientes cliente) {
        String sql = "UPDATE clientes SET nome =?, telefone =?, email =?, endereco =?, ativo=? WHERE id_cliente = ?";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            // define os valores dos parâmetros na query
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setBoolean(5, cliente.isAtivo());
            stmt.setInt(6, cliente.getIdCliente());

            // executa a query e verifica se algum registro foi atualizado
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            }
        } catch (SQLException e) {
            // trata possíveis erros na atualização do cliente
            System.out.println("Erro ao atualizar cliente");
            e.printStackTrace();
        }
    }

    // método para deletar um cliente do banco de dados
    public void deletarCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente =?";

        try (Connection conn = DbConnection.getConnection(); // abre conexão com o banco
                PreparedStatement stmt = conn.prepareStatement(sql)) { // prepara a query SQL

            stmt.setInt(1, idCliente); // define o ID do cliente como parâmetro

            // executa a query e verifica se algum registro foi deletado
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente removido com sucesso!");
            }
        } catch (SQLException e) {
            // trata possíveis erros na remoção do cliente
            System.out.println("Erro ao remover cliente");
            e.printStackTrace();
        }
    }
}
