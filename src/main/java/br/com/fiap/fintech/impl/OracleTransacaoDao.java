package br.com.fiap.fintech.impl;

import br.com.fiap.fintech.dao.ConnectionManager;
import br.com.fiap.fintech.dao.TransacaoDao;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.model.Cliente;
import br.com.fiap.fintech.model.Transacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OracleTransacaoDao implements TransacaoDao {

    private Connection conexao;

    @Override
    public void depositar(int idCliente, double valor) throws DBException {
        String sqlGetIdConta = "SELECT id_conta FROM conta WHERE id_cliente = ?";

        String sqlGetSaldoAtual = "SELECT saldo FROM transacao WHERE id_conta = ? ORDER BY dt_transacao DESC LIMIT 1";

        String sqlInserirTransacao = "INSERT INTO transacao (tipo_transacao, valor, dt_transacao, id_conta) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement stmtGetIdConta = null;
        PreparedStatement stmtGetSaldoAtual = null;
        PreparedStatement stmtInserirTransacao = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            stmtGetIdConta = conexao.prepareStatement(sqlGetIdConta);
            stmtGetIdConta.setInt(1, idCliente);
            rs = stmtGetIdConta.executeQuery();

            if (!rs.next()) {
                throw new DBException("Conta não encontrada para o cliente.");
            }

            int idConta = rs.getInt("id_conta");

            stmtGetSaldoAtual = conexao.prepareStatement(sqlGetSaldoAtual);
            stmtGetSaldoAtual.setInt(1, idConta);
            rs = stmtGetSaldoAtual.executeQuery();

            double saldoAtual = 0.0;
            if (rs.next()) {
                saldoAtual = rs.getDouble("saldo");
            }

            stmtInserirTransacao = conexao.prepareStatement(sqlInserirTransacao);
            stmtInserirTransacao.setString(1, "DEPÓSITO");
            stmtInserirTransacao.setDouble(2, valor);
            stmtInserirTransacao.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmtInserirTransacao.setInt(4, idConta);

            int rowsAffected = stmtInserirTransacao.executeUpdate();

            if (rowsAffected == 0) {
                throw new DBException("Erro ao registrar a transação.");
            }

            System.out.println("Depósito realizado com sucesso!");

        } catch (SQLException e) {
            throw new DBException("Erro ao realizar depósito: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtGetIdConta != null) stmtGetIdConta.close();
                if (stmtGetSaldoAtual != null) stmtGetSaldoAtual.close();
                if (stmtInserirTransacao != null) stmtInserirTransacao.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                throw new DBException("Erro ao fechar a conexão: " + e.getMessage(), e);
            }
        }
    }


    public void sacar(int idCliente, double valor) throws DBException {
        String sqlGetIdConta = "SELECT id_conta FROM conta WHERE id_cliente = ?";

        String sqlGetSaldoAtual = "SELECT saldo FROM transacao WHERE id_conta = ? ORDER BY dt_transacao DESC LIMIT 1";

        String sqlInserirTransacao = "INSERT INTO transacao (tipo_transacao, valor, dt_transacao, id_conta) VALUES (?, ?, ?, ?)";

        Connection conexao = null;
        PreparedStatement stmtGetIdConta = null;
        PreparedStatement stmtGetSaldoAtual = null;
        PreparedStatement stmtInserirTransacao = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();

            stmtGetIdConta = conexao.prepareStatement(sqlGetIdConta);
            stmtGetIdConta.setInt(1, idCliente);
            rs = stmtGetIdConta.executeQuery();

            if (!rs.next()) {
                throw new DBException("Conta não encontrada para o cliente.");
            }

            int idConta = rs.getInt("id_conta");

            stmtGetSaldoAtual = conexao.prepareStatement(sqlGetSaldoAtual);
            stmtGetSaldoAtual.setInt(1, idConta);
            rs = stmtGetSaldoAtual.executeQuery();

            double saldoAtual = 0.0;
            if (rs.next()) {
                saldoAtual = rs.getDouble("saldo");
            }

            if (saldoAtual < valor) {
                throw new DBException("Saldo insuficiente para realizar o saque.");
            }


            stmtInserirTransacao = conexao.prepareStatement(sqlInserirTransacao);
            stmtInserirTransacao.setString(1, "SAQUE");
            stmtInserirTransacao.setDouble(2, valor);
            stmtInserirTransacao.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            stmtInserirTransacao.setInt(4, idConta);


            int rowsAffected = stmtInserirTransacao.executeUpdate();

            if (rowsAffected == 0) {
                throw new DBException("Erro ao registrar a transação.");
            }


            System.out.println("Saque realizado com sucesso!");

        } catch (SQLException e) {
            throw new DBException("Erro ao realizar saque: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmtGetIdConta != null) stmtGetIdConta.close();
                if (stmtGetSaldoAtual != null) stmtGetSaldoAtual.close();
                if (stmtInserirTransacao != null) stmtInserirTransacao.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                throw new DBException("Erro ao fechar a conexão: " + e.getMessage(), e);
            }
        }
    }



    @Override
    public List<Transacao> listarTransacoes() throws DBException {

        List<Transacao> lista = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conexao = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM transacao " +
                    "INNER JOIN conta " +
                    "ON transacao.id_conta = conta.id_conta ";
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_transacao = rs.getInt("id_transacao");
                String tipoTransacao = rs.getString("tipo_transacao");
                int valor = rs.getInt("valor");
                String email = rs.getString("email");
                java.sql.Date dtTransacao = rs.getDate("dt_transacao");
                int idConta = rs.getInt("id_conta");

                Transacao transacao = new Transacao(id_transacao, tipoTransacao, valor, email, dtTransacao, idConta);
                lista.add(transacao);


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
        } return lista;
    }
}


