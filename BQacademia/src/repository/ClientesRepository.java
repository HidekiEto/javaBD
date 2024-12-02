package repository;

import models.Clientes;
import config.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesRepository {

    public void adicionarCliente(Clientes cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, data_nasc, telefone, email, endereco, data_cadastro, ativo) VALUES (?,?,?,?,?,?,?,?) ";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNasc()));
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getEndereco());
            stmt.setDate(7, java.sql.Date.valueOf(cliente.getDataCadastro()));
            stmt.setBoolean(8, cliente.isAtivo());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente adicionado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar cliente.");
            e.printStackTrace();
        }
    }

    public List<Clientes> obterTodosClientes() {
        List<Clientes> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Connection conn = DbConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

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
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter clientes.");
            e.printStackTrace();
        }
        return clientes;
    }

    public Clientes obterClientePorId(int idCliente) {
        String sql = "SELECT * FROM clientes WHERE id_cliente = ?";
        Clientes cliente = null;

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

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
            System.out.println("Erro ao obter cliente pelo ID");
            e.printStackTrace();
        }
        return cliente;
    }

    public void atualizarCliente(Clientes cliente) {
        String sql = "UPDATE clientes SET nome =?, telefone =?, email =?, endereco =?, ativo=?  WHERE id_cliente = ?";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(1, cliente.getTelefone());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getEndereco());
            stmt.setBoolean(4, cliente.isAtivo());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente atualizado com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente.");
            e.printStackTrace();
        }
    }

    public void deletarCliente(int idCliente) {
        String sql = "DELETE FROM clientes WHERE id_cliente =?";

        try (Connection conn = DbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Cliente removido com sucesso!");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover cliente.");
            e.printStackTrace();
        }
    }
}
