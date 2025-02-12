package br.com.fiap.fintech.impl;

import br.com.fiap.fintech.dao.ClienteDao;
import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleClienteDao implements ClienteDao {

    private Connection conexao;

    @Override
    public void cadastrarCliente(Cliente cliente) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO cliente " + "(id_cliente, dt_de_cadastro, nome, email, cpf, rg, dt_nascimento) "
                + "VALUES (SQ_cliente.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(cliente.getDtDeCadastro()));
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getCpf());
            stmt.setString(5, cliente.getRg());
            stmt.setDate(6, java.sql.Date.valueOf(cliente.getDtNascimento()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar o cliente", e);

        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    @Override
    public void atualizarCliente(Cliente cliente) throws DBException {
        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "UPDATE cliente SET nome = ?, email = ?, cpf = ?, rg = ?, dt_nascimento = ? WHERE id_cliente = ?";
            stmt = conexao.prepareStatement(sql);

            System.out.println("[DEBUG] Preparando atualização para o cliente com ID: " + cliente.getIdCliente());
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getRg());
            stmt.setDate(5, Date.valueOf(cliente.getDtNascimento()));
            stmt.setInt(6, cliente.getIdCliente());

            System.out.println("[DEBUG] Executando atualização no banco de dados...");
            int rowsAffected = stmt.executeUpdate();
            System.out.println("[DEBUG] Linhas afetadas: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar o cliente", e);
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
                System.out.println("[DEBUG] Conexão fechada após atualização.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removerCliente(int id) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM cliente WHERE id_cliente = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao remover o cliente", e);
        } finally {
            try {
                stmt.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Cliente buscarCliente(int id) throws DBException {
        Cliente cliente = null;

        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        try (
                Connection conexao = ConnectionManager.getInstance().getConnection();
                PreparedStatement stmt = conexao.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idCliente = rs.getInt("id_cliente");
                    String nome = rs.getString("nome");
                    String email = rs.getString("email");
                    String cpf = rs.getString("cpf");
                    String rg = rs.getString("rg");
                    java.sql.Date dtNascimento = rs.getDate("dt_nascimento");

                    cliente = new Cliente(idCliente, nome, email, cpf, rg, dtNascimento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarCliente() throws DBException {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT id_cliente, dt_de_cadastro, nome, cpf, rg, email, dt_nascimento FROM cliente";

        try (Connection conexao = ConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                String dtDeCadastro = rs.getString("dt_de_cadastro");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                java.sql.Date dtNascimento = rs.getDate("dt_nascimento");

                // Cria o objeto Cliente e o adiciona à lista
                Cliente cliente = new Cliente(idCliente, dtDeCadastro, nome, cpf, rg, email, dtNascimento);
                lista.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao listar clientes", e);
        }

        return lista;
    }
}