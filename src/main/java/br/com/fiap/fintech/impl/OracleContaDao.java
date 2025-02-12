package br.com.fiap.fintech.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.ContaDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Conta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OracleContaDao implements ContaDao {

    private Connection conexao;

    @Override
    public void cadastrarConta(Conta conta) throws DBException {

        PreparedStatement stmt = null;

        conexao = ConnectionManager.getInstance().getConnection();

        String sql = "INSERT INTO conta " + "(id_conta, tipo_conta, saldo, dt_de_abertura, cliente_id_cliente) "
                + "VALUES (SQ_conta.NEXTVAL, ?, ?, ?, ?)";
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, conta.getTipoConta());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setDate(3, Date.valueOf(conta.getDtDeAbertura()));
            stmt.setInt(4, conta.getIdCliente().getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao cadastrar a conta", e);
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
    public void atualizarConta(Conta conta) throws DBException {

        PreparedStatement stmt = null;
        try {
            conexao = ConnectionManager.getInstance().getConnection();

            String sql = "UPDATE cliente SET " + "tipo_conta = ?, " + "saldo = ?, " + "dt_de_abertura = ? WHERE id_conta = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, conta.getTipoConta());
            stmt.setDouble(2, conta.getSaldo());
            stmt.setDate(3, Date.valueOf(conta.getDtDeAbertura()));


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar a conta", e);
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
    public void removerConta(int id) throws DBException {

        PreparedStatement stmt = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "DELETE FROM conta WHERE id_conta = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DBException("Erro ao remover a conta", e);
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
    public Conta buscarConta(int id) throws DBException {

        Conta conta = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;


        // testar sintaxe sql:
        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM conta " +
                    "INNER JOIN cliente " +
                    "ON conta.id_cliente = cliente.id_cliente " +
                    "WHERE id_conta = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idConta = rs.getInt("id_conta");
                String tipoConta = rs.getString("tipo_conta");
                int saldo = rs.getInt("saldo");
                int idCliente = rs.getInt("id_cliente");

                conta = new Conta(idConta, tipoConta, saldo, idCliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conta;
    }

    @Override
    public List<Conta> listarContas(int idCliente) throws DBException {

        List<Conta> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            // Corrigindo a consulta para garantir que estamos pegando as contas relacionadas ao id_cliente
            String sql = "SELECT * FROM conta WHERE cliente_id_cliente = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idCliente); // Passando o idCliente como parâmetro na consulta SQL

            rs = stmt.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setIdConta(rs.getInt("id_conta"));
                conta.setTipoConta(rs.getString("tipo_conta"));
                conta.setSaldo(rs.getDouble("saldo"));
                conta.setDtDeAbertura(rs.getDate("dt_de_abertura").toLocalDate());
                conta.setClienteId(rs.getInt("cliente_id_cliente"));
                lista.add(conta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
}